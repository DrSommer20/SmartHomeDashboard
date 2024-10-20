// routine.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoutineService {
  private apiUrl = 'https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/api/routine';

  constructor(private http: HttpClient) { }

  getRoutines(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  switchRoutine(routineId: string, state: string): Observable<any> {
    const url = `${this.apiUrl}/${routineId}/${state}`;

    return this.http.post(url, {});
  }

  deleteRoutine(routineId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${routineId}`);
  }
}
