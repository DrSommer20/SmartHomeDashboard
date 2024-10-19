// src/app/weather/weather.component.ts
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import * as weatherCodes from '../../../public/json/weathercodes.json';

@Component({
  selector: 'an-weather',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './weather.component.html',
  styleUrl: './weather.component.css'
})
export class WeatherComponent implements OnInit {
  currentWeather: any;
  forecast: any[] = [];



  locationError: string = '';
  weatherIcons: { [key: string]: { day: { description: string, image: string }, night: { description: string, image: string } } } = {};

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.weatherIcons = weatherCodes;
    this.getLocation();
  }


  getLocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const lat = position.coords.latitude;
          const lon = position.coords.longitude;
          this.getWeather(lat, lon);
        },
        (error) => {
          this.locationError = 'Unable to retrieve your location';
          console.error('Geolocation error:', error);
        }
      );
    } else {
      this.locationError = 'Geolocation is not supported by this browser.';
    }
  }

  getWeather(lat: number, lon: number): void {
    const apiUrl = `https://api.open-meteo.com/v1/forecast?latitude=${lat}&longitude=${lon}&current_weather=true&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,weathercode&timezone=auto`;

    this.http.get<any>(apiUrl).subscribe(
      (response) => {
        this.currentWeather = response.current_weather;
        this.forecast = response.daily.time.map((time: string, index: number) => ({
          time,
          temperature_2m_max: response.daily.temperature_2m_max[index],
          temperature_2m_min: response.daily.temperature_2m_min[index],
          precipitation_sum: response.daily.precipitation_sum[index],
          weathercode: response.daily.weathercode[index]
        })).slice(1, 4);
      },
      (error) => {
        console.error('Error fetching weather data:', error);
      }
    );
  }

    
  getWeatherIcon(weatherCode: number, isDaytime: boolean): string {
    if (!this.weatherIcons || !this.weatherIcons[weatherCode.toString()]) {
      console.warn(`Weather icon for code ${weatherCode.toString()} not found`);
      return 'assets/icons/default.png'; // Use a default icon if the data is not available
    }

    const timeOfDay = isDaytime ? 'day' : 'night';
    const icon = this.weatherIcons[weatherCode.toString()][timeOfDay]?.image;
    if (!icon) {
      console.warn(`Weather icon for code ${weatherCode} and time of day ${timeOfDay} not found`);
      return 'assets/icons/default.png'; // Use a default icon if the specific icon is not available
    }
    return icon;
  }

  isDaytime(): boolean {
    const hour = new Date().getHours();
    return hour >= 6 && hour < 18;
  }
}