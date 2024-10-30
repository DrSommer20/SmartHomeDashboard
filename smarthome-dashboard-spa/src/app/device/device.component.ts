import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  signal,
  ViewChild,
  ViewContainerRef,
  ComponentRef,
} from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ModalComponent } from '../modal/modal.component';
import { Apollo, QueryRef, gql } from 'apollo-angular';

@Component({
  selector: 'an-device',
  standalone: true,
  imports: [CommonModule, ModalComponent],
  templateUrl: './device.component.html',
  styleUrl: './device.component.css',
})
export class AllDevicesComponent implements OnInit, AfterViewInit, OnDestroy {
  title = 'All Devices';
  devices = signal<any[]>([]);
  public refreshInterval: any;
  isDevicesRoute: boolean = false;
  postsQuery!: QueryRef<any>;
  @ViewChild('modalContainer', { read: ViewContainerRef })
  modalContainer!: ViewContainerRef;
  private modalRef!: ComponentRef<ModalComponent>;

  private querySubscription!: Subscription;

  constructor(private router: Router, private readonly apollo: Apollo) {
    this.router.events.subscribe(() => {
      this.isDevicesRoute = this.router.url === '/home/devices';
    });
  }

  ngOnInit(): void {
    this.postsQuery = this.apollo.watchQuery<any>({
      query: GET_DEVICES,
      pollInterval: 20000,
    });
    this.querySubscription = this.postsQuery.valueChanges.subscribe(
      ({ data, loading }) => {
        this.devices.set(data.allDevices);
      }
    );
    
  }

  ngAfterViewInit(): void {
    this.router.events.subscribe(() => {
      this.isDevicesRoute = this.router.url === '/home/devices';
    });
  }

  ngOnDestroy() {
    this.querySubscription.unsubscribe();
  }

  updateDevices(): void {
    this.postsQuery.refetch();
  }

  toggleDevice(device: any, event: any): void {
    const isChecked = event.target.checked;
    const newState = isChecked ? 'on' : 'off';

    this.apollo
      .mutate({
        mutation: SWITCH_DEVICE,
        variables: {
          id: device.id,
          state: newState,
        },
      })
      .subscribe(
        (response) => {
          this.updateDevices();
        },
        (error) => {
          console.error('Error switching device:', error);
        }
      );
  }

  deleteDevice(deviceId: string): void {
  this.modalRef = this.modalContainer.createComponent(ModalComponent);
  this.modalRef.instance.type = 'ConfirmDelete';

  this.modalRef.instance.onConfirm.subscribe(() => {
    this.modalRef.destroy();
    this.apollo
      .mutate({
        mutation: DELETE_DEVICE,
        variables: {
          id: deviceId,
        },
      })
      .subscribe(
        (response) => {
          this.updateDevices();
        },
        (error) => {
          console.error('Error deleting device:', error);
        }
      );
    });
    this.modalRef.instance.onCancel.subscribe(() => {
      this.modalRef.destroy();
    });
  }


  editDevice(deviceId: string): void {
    this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'EditDevice';
    this.modalRef.instance.data = { deviceId };
    this.modalRef.instance.close.subscribe(() => {
      this.modalRef.destroy();
      this.updateDevices();
    });
  }
}

const GET_DEVICE_BY_ID = gql`
  query GetDeviceById($id: ID!) {
    deviceById(id: $id) {
      id
      name
      type {
        id
        name
        icon
      }
      room {
        id
        name
      }
      status
      state
    }
  }
`;

const GET_DEVICES = gql`
  query GetAllDevices {
    allDevices {
      id
      name
      type {
        id
        name
        icon
      }
      room {
        id
        name
      }
      status
      state
    }
  }
`;

const SWITCH_DEVICE = gql`
  mutation SwitchDevice($id: ID!, $state: String!) {
    switchDevice(id: $id, state: $state) {
      id
      state
    }
  }
`;

const DELETE_DEVICE = gql`
  mutation DeleteDevice($id: ID!) {
    deleteDevice(id: $id)
  }
`;
