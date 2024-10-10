import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private apiUrl = 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/device';

  constructor(private http: HttpClient) { }

  getDevices(): Observable<any> {
    //const headers = new HttpHeaders({
    //  'Authorization': localStorage.getItem('authToken') || ''
    //});
    return this.http.get(this.apiUrl, {  });
  }

  switchDevice(deviceId: string, state: string): Observable<any> {
    const url = `${this.apiUrl}/${deviceId}/switch/${state}`;
    //const headers = new HttpHeaders({
    //  'Authorization': localStorage.getItem('authToken') || ''
    //});
    return this.http.post(url, {}, {  });
  }

  getDeviceStatus(deviceId: string): Observable<any> {
    const url = `${this.apiUrl}/${deviceId}`;
    //const headers = new HttpHeaders({
    //  'Authorization': localStorage.getItem('authToken') || ''
    //});
    return this.http.get(url, {  });
  }
}
