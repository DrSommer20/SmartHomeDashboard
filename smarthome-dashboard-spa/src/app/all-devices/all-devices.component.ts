import { Component, OnDestroy, OnInit, effect, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeviceService } from './all-devices.service';

@Component({
  selector: 'an-all-devices',
  standalone: true, // Standalone component
  imports: [CommonModule], // Import CommonModule for template directives like *ngIf and *ngFor
  templateUrl: './all-devices.component.html',
  styleUrl: './all-devices.component.css'
})
export class AllDevicesComponent implements OnInit, OnDestroy {
  // Using signals for reactive state management in Angular 17
  devices = signal<any[]>([]);
  public refreshInterval: any;
  constructor(private deviceService: DeviceService) { }
  
  ngOnInit(): void {
    this.updateDevices();
    this.refreshInterval = setInterval(() => {
      this.updateDevices();
    }, 30000); // 30 seconds interval
  }

  ngOnDestroy(): void {
    clearInterval(this.refreshInterval);
  }

  updateDevices(): void {
    this.deviceService.getDevices().subscribe(
      response => {
        console.log('Erfolgreiche Antwort:', response);
        this.devices.set(response.devices || []);
      },
      error => {
        console.error('Fehler bei der Anfrage:', error);
      }
    );
  }

  toggleDevice(device: any, event: any): void {
    const isChecked = event.target.checked;
    const newState = isChecked ? 'on' : 'off';

    this.deviceService.switchDevice(device.device_id, newState).subscribe(
      response => {
        console.log('Device updated successfully:', response);
        this.refreshDeviceStatus(device.device_id);
      },
      error => {
        console.error('Error updating device:', error);
        event.target.checked = !isChecked; // Revert state on error
      }
    );
  }

  refreshDeviceStatus(deviceId: string): void {
    this.deviceService.getDeviceStatus(deviceId).subscribe(
      response => {
        console.log('Erfolgreiche Antwort:', response);
        const deviceIndex = this.devices().findIndex(d => d.device_id === deviceId);
        if (deviceIndex !== -1) {
          const updatedDevices = [...this.devices()];
          updatedDevices[deviceIndex].state = response.state;
          updatedDevices[deviceIndex].status = response.status;
          this.devices.set(updatedDevices);
        }
      },
      error => {
        console.error('Fehler bei der Anfrage:', error);
      }
    );
  }
}

