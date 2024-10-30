import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';
import { Subscription } from 'rxjs';
import { Apollo, QueryRef, gql } from 'apollo-angular';

@Component({
  selector: 'an-add-device',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-device.component.html',
  styleUrl: './add-device.component.css',
})
export class AddDeviceComponent implements OnInit {
  @Output() deviceAdded = new EventEmitter<void>();
  devices: any[] = [];
  deviceTypes: any[] = [];
  rooms: any[] = [];
  deviceForm: FormGroup;

  constructor(private fb: FormBuilder, private readonly apollo: Apollo) {
    this.deviceForm = this.fb.group({
      smartThings: [''],
      name: [''],
      type: [''],
      location: [''],
    });
  }

  ngOnInit(): void {
    this.apollo
      .watchQuery<any>({
        query: ON_LOAD,
      })
      .valueChanges.subscribe(({ data }) => {
        this.devices = data.smartThingsDevices;
        this.deviceTypes = data.allDeviceTypes;
        this.rooms = data.allRooms;
      });
  }

  addDevice(): void {
    const formData = this.deviceForm.value;

    this.apollo
      .mutate({
        mutation: ADD_DEVICE,
        variables: {
          id: formData.smartThings,
          name: formData.name,
          typeId: formData.type,
          roomId: formData.location,
        },
      })
      .subscribe(
        (response) => {
          this.deviceAdded.emit();
        },
        (error) => {
          console.error('Error deleting device:', error);
        }
      );
  }
}

const ADD_DEVICE = gql`
  mutation CreateDevice($id: ID!, $name: String!, $typeId: ID!, $roomId: ID!) {
    createDevice(id: $id, name: $name, typeId: $typeId, roomId: $roomId)
  }
`;

const ON_LOAD = gql`
  query OnLoad {
    allRooms {
      id
      name
    }
    smartThingsDevices {
      id
      name
    }
    allDeviceTypes {
      id
      name
    }
  }
`;
