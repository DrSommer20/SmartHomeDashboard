// auth.interceptor.ts
import { LoginService } from './login/login.service'; 
import { HttpInterceptorFn } from "@angular/common/http";
import { inject } from '@angular/core';
import { tap } from "rxjs";

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
    console.log('request', req.method, req.url);
    console.log('authInterceptor')

    const loginService = inject(LoginService); // Inject LoginService

    if (req.url.startsWith('https://smarthomebackend-spontaneous-bilby-ni.apps.01.cf.eu01.stackit.cloud/')) {
      const authToken = loginService.getToken();
      if (authToken) {
        const headers = req.headers.set('Authorization', authToken);

        req = req.clone({
            headers
        });
      }
    }

    return next(req).pipe(
        tap(resp => console.log('response', resp))
    );

}