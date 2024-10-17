import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(public jwtHelper: JwtHelperService, private http:HttpClient) { }

  getToken(): string | null {
    return sessionStorage.getItem('authToken') || localStorage.getItem('authToken')
  }

  isLoggedIn(): boolean {
    const token = sessionStorage.getItem('authToken') || localStorage.getItem('authToken');
    const helper = new JwtHelperService();
    const isExpired = helper.isTokenExpired(token);
    return !isExpired;
  }
  


  logout(): void {
    localStorage.removeItem('authToken'); // Clear token from storage
    sessionStorage.removeItem('authToken');
  }
}
