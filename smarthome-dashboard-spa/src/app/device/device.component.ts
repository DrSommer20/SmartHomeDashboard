import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnDestroy, OnInit, signal } from '@angular/core';
import { DeviceService } from './device.service';
import { Router } from '@angular/router';

@Component({
  selector: 'an-device',
  standalone: true, 
  imports: [CommonModule], 
  templateUrl: './device.component.html',
  styleUrl: './device.component.css'
})
export class AllDevicesComponent implements OnInit, AfterViewInit, OnDestroy {
  title = 'All Devices';
  devices = signal<any[]>([]);
  public refreshInterval: any;
  isDevicesRoute: boolean = false;

  constructor(private deviceService: DeviceService,  private router: Router) {
    this.router.events.subscribe(() => {
      this.isDevicesRoute = this.router.url === '/home/devices';
    });
   }
  
  ngOnInit(): void {
    this.updateDevices();
    this.refreshInterval = setInterval(() => {
      this.updateDevices();
    }, 30000); // 30 seconds interval
  }

  ngAfterViewInit(): void {
    this.router.events.subscribe(() => {
      this.isDevicesRoute = this.router.url === '/home/devices';
    });
  }

  ngOnDestroy(): void {
    clearInterval(this.refreshInterval);
  }

  updateDevices(): void {
    this.deviceService.getDevices().subscribe(
      response => {
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
        this.updateDevices();
      },
      error => {
        console.error('Error updating device:', error);
        event.target.checked = !isChecked; // Revert state on error
      }
    );
  }

  deleteDevice(deviceId: string): void {
    this.deviceService.deleteDevice(deviceId).subscribe(
      response => {
        this.updateDevices(); // Refresh the list of devices
      },
      error => {
        console.error('Error deleting device:', error);
      }
    );
  }

  refreshContent(): void {
    this.updateDevices();
  }

  editDevice(deviceId: string): void {
    // Implement edit device
  }
  
}

