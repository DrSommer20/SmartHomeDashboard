import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ViewContainerRef, ComponentRef, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddDeviceComponent } from '../add-device/add-device.component';
import { AddRoutineComponent } from '../add-routine/add-routine.component';
import { AddRoomComponent } from '../add-room/add-room.component';

@Component({
  selector: 'an-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent implements AfterViewInit {
  @Output() close = new EventEmitter<void>();
  @Input() type: string = 'default';
  @ViewChild('dynamicComponentContainer', { read: ViewContainerRef }) container!: ViewContainerRef;
  private componentRef!: ComponentRef<any>;


  ngAfterViewInit() {
    this.loadComponent();
  }

  loadComponent() {
    console.log(this.type);
    switch (this.type) {
      case 'Device':
        this.componentRef = this.container.createComponent(AddDeviceComponent);
        this.componentRef.instance.deviceAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'Routine':
        this.componentRef = this.container.createComponent(AddRoutineComponent);
        this.componentRef.instance.routineAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'Rooms':
        this.componentRef = this.container.createComponent(AddRoomComponent);
        this.componentRef.instance.roomAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      default:
        break;
    }
  }

  closeModal() {
    this.close.emit();
  }
}
