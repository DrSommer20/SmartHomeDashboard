// auth.interceptor.ts
import { Injectable, inject } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login/login.service'; // Import the service that handles token management

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private loginService = inject(LoginService); // Using Angular 17 inject API

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken = this.loginService.getToken(); // Get the stored JWT token

    // If the token exists, clone the request and add the Authorization header
    if (authToken) {
      const clonedRequest = req.clone({
        headers: req.headers.set('Authorization', `${authToken}`),
      });

      // Pass the cloned request with the token to the next handler
      return next.handle(clonedRequest);
    }

    // If no token, pass the original request without modification
    return next.handle(req);
  }
}
