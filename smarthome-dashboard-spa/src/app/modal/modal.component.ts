import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  ViewChild,
  ViewContainerRef,
  ComponentRef,
  AfterViewInit,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddDeviceComponent } from '../add-device/add-device.component';
import { AddRoutineComponent } from '../add-routine/add-routine.component';
import { AddRoomComponent } from '../add-room/add-room.component';
import { EditDeviceComponent } from '../edit-device/edit-device.component';
import { EditRoomComponent } from '../edit-room/edit-room.component';
import { EditRoutineComponent } from '../edit-routine/edit-routine.component';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'an-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css',
})
export class ModalComponent implements AfterViewInit {
  @Output() close = new EventEmitter<void>();
  @Output() onConfirm = new EventEmitter<void>();
  @Output() onCancel = new EventEmitter<void>();
  @Input() type: string = 'default';
  @Input() data: any;
  @ViewChild('dynamicComponentContainer', { read: ViewContainerRef })
  container!: ViewContainerRef;
  private componentRef!: ComponentRef<any>;

  ngAfterViewInit() {
    this.loadComponent();
  }

  loadComponent() {
    switch (this.type) {
      case 'Device':
        this.componentRef = this.container.createComponent(AddDeviceComponent);
        this.componentRef.instance.deviceAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'EditDevice':
        this.componentRef = this.container.createComponent(EditDeviceComponent);
        this.componentRef.instance.deviceId = this.data.deviceId;
        this.componentRef.instance.deviceUpdated.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'Routine':
        this.componentRef = this.container.createComponent(AddRoutineComponent);
        this.componentRef.instance.routineAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'EditRoutine':
        this.componentRef =
          this.container.createComponent(EditRoutineComponent);
        this.componentRef.instance.routineId = this.data.routineId;
        this.componentRef.instance.routineUpdated.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'Rooms':
        this.componentRef = this.container.createComponent(AddRoomComponent);
        this.componentRef.instance.roomAdded.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'EditRoom':
        this.componentRef = this.container.createComponent(EditRoomComponent);
        this.componentRef.instance.roomId = this.data.roomId;
        this.componentRef.instance.roomUpdated.subscribe(() => {
          this.closeModal();
        });
        break;
      case 'ConfirmDelete':
        this.componentRef = this.container.createComponent(
          ConfirmDialogComponent
        );
        this.componentRef.instance.onConfirm.subscribe(() => {
          this.onConfirm.emit();
        });
        this.componentRef.instance.onCancel.subscribe(() => {
          this.onCancel.emit();
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
