import { EventEmitter, Injectable, OnInit, Output } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeviceService implements OnInit {
  @Output() titleEvent = new EventEmitter<string>();
  private apiUrl = 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device';

  ngOnInit(): void {
    this.titleEvent.emit('Devices');
  }
  constructor(private http: HttpClient) { }

  getDevices(): Observable<any> {
    return this.http.get(this.apiUrl, {  });
  }

  switchDevice(deviceId: string, state: string): Observable<any> {
    const url = `${this.apiUrl}/${deviceId}/switch/${state}`;
    return this.http.post(url, {}, {  });
  }

  getDeviceStatus(deviceId: string): Observable<any> {
    const url = `${this.apiUrl}/${deviceId}`;
    return this.http.get(url, {  });
  }
}
