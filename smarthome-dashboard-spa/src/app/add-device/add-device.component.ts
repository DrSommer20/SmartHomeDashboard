import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { CommonModule } from '@angular/common';
import { tap } from 'rxjs';

@Component({
  selector: 'an-add-device',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './add-device.component.html',
  styleUrl: './add-device.component.css'
})
export class AddDeviceComponent implements OnInit {
    @Output() deviceAdded = new EventEmitter<void>();
    devices: any[] = [];
    deviceForm: FormGroup;
  
    constructor(private fb: FormBuilder, private http: HttpClient) {
      this.deviceForm = this.fb.group({
        smartThings: [''],
        name: [''],
        type: [''],
        location: ['']
      });
    }
  
    ngOnInit(): void {
      this.loadSmartThingsDevices();
    }
  
    loadSmartThingsDevices(): void {

  
      this.http.get<any>('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device/smartthings')
        .pipe(
          tap(response => {
            console.log('Erfolgreiche Antwort:', response);
            this.devices = response.devices;
          })
        )
        .subscribe({
          next: () => {},
          error: error => console.error('Fehler bei der Anfrage:', error)
        });
    }
  
    addDevice(): void {
      const formData = this.deviceForm.value;
  
      const newDevice = {
        device_id: formData.smartThings,
        name: formData.name,
        type: formData.type,
        location: formData.location
      };
  
      console.log(newDevice);
  
      this.http.post('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device', newDevice)
        .pipe(
          tap(() => {
            this.deviceAdded.emit();
          })
        )
        .subscribe({
          next: () => {},
          error: error => console.error('Error: ' + error.status + ' ' + error.message)
        });
    }
  }