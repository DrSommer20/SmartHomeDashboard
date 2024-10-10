import { Component } from '@angular/core';
import { AllDevicesComponent } from '../all-devices/all-devices.component'; 

@Component({
  selector: 'an-dashboard',
  standalone: true,
  imports: [AllDevicesComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
