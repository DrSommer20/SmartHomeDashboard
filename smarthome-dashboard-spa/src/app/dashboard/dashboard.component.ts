import { Component } from '@angular/core';
import { AllDevicesComponent } from '../device/device.component';
import { WeatherComponent } from '../weather/weather.component';

@Component({
  selector: 'an-dashboard',
  standalone: true,
  imports: [AllDevicesComponent, WeatherComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {}
