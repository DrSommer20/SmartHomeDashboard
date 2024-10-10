import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(public jwtHelper: JwtHelperService) { }

  getToken(): string | null {
    if (!localStorage.getItem('authToken')) {
      if (!sessionStorage.getItem('authToken')) {
        return null
      }
      else {
        return sessionStorage.getItem('authToken');
      }
    }
    else {
      return localStorage.getItem('authToken');
    }
  }

  isLoggedIn(): boolean {
    let token;
    if (!localStorage.getItem('authToken')) {
      if (!sessionStorage.getItem('authToken')) {
        return false
      }
      else {
        token = sessionStorage.getItem('authToken');
      }      
    }
    else {
      token = localStorage.getItem('authToken');
    }
    return !this.jwtHelper.isTokenExpired(token);
  }

  logout(): void {
    localStorage.removeItem('authToken'); // Clear token from storage
    sessionStorage.removeItem('authToken');
  }
}
