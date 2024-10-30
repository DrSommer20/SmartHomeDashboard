import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Apollo, gql } from 'apollo-angular';
import { tap, switchMap } from 'rxjs/operators';

@Component({
  selector: 'an-edit-device',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './edit-device.component.html',
  styleUrl: './edit-device.component.css',
})
export class EditDeviceComponent implements OnInit {
  @Input() deviceId!: string;
  @Output() deviceUpdated = new EventEmitter<void>();
  deviceForm: FormGroup;
  deviceTypes: any[] = [];
  rooms: any[] = [];

  constructor(private fb: FormBuilder, private apollo: Apollo) {
    this.deviceForm = this.fb.group({
      name: [''],
      type: [''],
      location: [''],
    });
  }

  ngOnInit(): void {
    this.loadAll()
      .pipe(switchMap(() => this.loadInitialData()))
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error loading initial data:', error),
      });
  }

  loadAll() {
    return this.apollo
      .watchQuery<any>({
        query: ON_LOAD,
      })
      .valueChanges.pipe(
        tap(({ data }) => {
          this.deviceTypes = data.allDeviceTypes;
          this.rooms = data.allRooms;
        })
      );
  }

  loadInitialData() {
    return this.apollo
      .query({
        query: GET_INITIAL_DATA,
        variables: { id: this.deviceId },
      })
      .pipe(
        tap((result: any) => {
          const device = result.data.deviceById;
          this.deviceForm.patchValue({
            name: device.name,
            type: device.type.id,
            location: device.room.id,
          });
        })
      );
  }

  updateDevice(): void {
    const formData = this.deviceForm.value;

    this.apollo
      .mutate({
        mutation: UPDATE_DEVICE,
        variables: {
          id: this.deviceId,
          name: formData.name,
          typeId: formData.type,
          roomId: formData.location,
        },
      })
      .pipe(
        tap(() => {
          this.deviceUpdated.emit();
        })
      )
      .subscribe({
        next: () => {},
        error: (error) => console.error('Error updating device:', error),
      });
  }
}

const ON_LOAD = gql`
  query OnLoad {
    allRooms {
      id
      name
    }
    allDeviceTypes {
      name
      id
    }
  }
`;

const GET_INITIAL_DATA = gql`
  query GetInitialData($id: ID!) {
    deviceById(id: $id) {
      id
      name
      type {
        id
        name
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

const UPDATE_DEVICE = gql`
  mutation UpdateDevice($id: ID!, $name: String, $typeId: ID, $roomId: ID) {
    updateDevice(id: $id, name: $name, typeId: $typeId, roomId: $roomId) {
      id
      name
      type {
        id
        name
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
