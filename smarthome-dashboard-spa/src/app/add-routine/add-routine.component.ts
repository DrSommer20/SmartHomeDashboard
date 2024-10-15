import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, FormArray, FormControl, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Device {
  device_id: string;
  name: string;
}

interface Action {
  device_id: string;
  device_name: string;
  action: string;
}

@Component({
  selector: 'an-add-routine',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-routine.component.html',
  styleUrl: './add-routine.component.css'
})

export class AddRoutineComponent implements OnInit {
  devices: Device[] = [];
  routineForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.routineForm = this.fb.group({
      routineName: [''],
      routineTime: [''],
      actions: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.loadDevices();
  }

  loadDevices(): void {
    this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device')
      .subscribe({
        next: (response) => {
          console.log('Erfolgreiche Antwort:', response);
          this.devices = response.devices;
        },
        error: (error) => {
          console.error('Fehler bei der Anfrage:', error);
        }
      });
  }

  addRoutineSubmit(event: Event): void {
    event.preventDefault();
    const formData = this.routineForm.value;
    const actions: Action[] = [];

    (formData.actions as Array<any>).forEach((action: any) => {
      const selectedDevice = this.devices.find(device => device.device_id === action.device_id);
      if (selectedDevice) {
        actions.push({ device_id: action.device_id, device_name: selectedDevice.name, action: action.action });
      }
    });

    const routineData = {
      name: formData.routineName,
      actions: actions,
      trigger: {
        type: 'time',
        value: formData.routineTime
      },
      state: true
    };

    this.http.post('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine', routineData)
      .subscribe({
        next: (data) => {
          console.log('Routine added', data);
          window.location.href = 'Routines.html';
        },
        error: (error) => {
          console.log('Error: ' + error.status + ' ' + error.message);
          alert('An error occurred during adding routine. Please try again.');
        }
      });
  }

  get actions(): FormArray {
    return this.routineForm.get('actions') as FormArray;
  }

  addAction(): void {
    this.actions.push(this.fb.group({
      device_id: [''],
      action: ['']
    }));
  }

  removeAction(index: number): void {
    this.actions.removeAt(index);
  }
}