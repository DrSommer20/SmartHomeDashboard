import { Component, Input, ViewChild, ComponentRef, ViewContainerRef, OnInit, OnDestroy, EventEmitter, Output } from '@angular/core';
import { ModalComponent } from '../modal/modal.component';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';

@Component({
  selector: 'an-content-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './content-header.component.html',
  styleUrl: './content-header.component.css'
})
export class ContentHeaderComponent implements OnInit, OnDestroy {
  @Input() title = 'Title of Page';
  @Input() buttonTitle: string = '';
  @Output() refreshContent = new EventEmitter<void>();
  @ViewChild('modalContainer', { read: ViewContainerRef }) modalContainer!: ViewContainerRef;
 
  private modalRef!: ComponentRef<ModalComponent>;
  private routerSubscription!: Subscription;
  currentRoute: string = '';

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.urlAfterRedirects;
      }
    });
  }

  ngOnInit() {
    this.buttonTitle = this.getRouteButton();
    this.routerSubscription = this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd),
        map(() => this.getRouteButton())
      )
      .subscribe((title: string) => {
        this.buttonTitle = title;
      });
  }

  ngOnDestroy() {
    if (this.routerSubscription) {
      this.routerSubscription.unsubscribe();
    }
  }

  closeModal() {
    if (this.modalRef) {
      this.modalRef.destroy();
    }
  }

  handleButtonClick(): void {
    switch (this.currentRoute) {
      case '/home/devices':
        this.showModalForDevice();
        break;
      case '/home/routines':
        this.showModalForRoutine();
        break;
      case '/home/rooms':
        this.showModalForRooms();
        break;
    }
  }

  showModalForDevice(): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'Device';
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.refreshContent.emit();
    });
  }

  showModalForRoutine(): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'Routine';
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.refreshContent.emit();
    });
  }

  showModalForRooms(): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'Rooms';
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.refreshContent.emit();
    });
  }

  private getRouteButton(): string {
    let route: ActivatedRoute = this.activatedRoute;
    let routeButton = '';
    while (route.firstChild) {
      route = route.firstChild;
    }
    if (route.snapshot.data['button']) {
      routeButton = route.snapshot.data['button'];
    }
    return routeButton;
  }
}