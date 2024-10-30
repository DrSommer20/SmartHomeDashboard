import {
  Component,
  Input,
  ViewChild,
  AfterViewChecked,
  OnInit,
} from '@angular/core';
import { ContentHeaderComponent } from '../content-header/content-header.component';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { RouterOutlet } from '@angular/router';
import { filter, map } from 'rxjs';

@Component({
  selector: 'an-main-content',
  standalone: true,
  imports: [ContentHeaderComponent, RouterOutlet],
  templateUrl: './main-content.component.html',
  styleUrl: './main-content.component.css',
})
export class MainContentComponent implements OnInit {
  @Input() marginLeft: string = '90px';
  @ViewChild(ContentHeaderComponent) contentHeader!: ContentHeaderComponent;
  @ViewChild(RouterOutlet) outlet!: RouterOutlet;

  pageTitle: string = 'Dashboard';

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.setPageTitle();
    this.contentHeader.refreshContent.subscribe(() => {
      this.refreshContent();
    });
  }
  ngAfterViewChecked() {
    this.router.events
      .pipe(
        filter((event) => event instanceof NavigationEnd),
        map(() => this.getRouteTitle())
      )
      .subscribe((title: string) => {
        if (title) {
          this.pageTitle = title;
        }
      });
  }

  refreshContent() {
    const activeComponent = this.outlet.component as {
      refreshContent?: () => void;
    };
    if (
      activeComponent &&
      typeof activeComponent.refreshContent === 'function'
    ) {
      activeComponent.refreshContent();
    }
  }

  private setPageTitle() {
    const title = this.getRouteTitle();
    if (title) {
      this.pageTitle = title;
    }
  }

  private getRouteTitle(): string {
    let route: ActivatedRoute = this.activatedRoute;
    let routeTitle = '';
    while (route.firstChild) {
      route = route.firstChild;
    }
    if (route.snapshot.data['title']) {
      routeTitle = route.snapshot.data['title'];
    }
    return routeTitle;
  }
}
