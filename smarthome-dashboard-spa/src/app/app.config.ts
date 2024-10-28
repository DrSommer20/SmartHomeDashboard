import {
  ApplicationConfig,
  importProvidersFrom,
  inject,
  provideZoneChangeDetection,
} from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http'; // Import provideHttpClient to provide HttpClientModule
import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { AuthInterceptor } from './auth.interceptor';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async'; // Import your interceptor
import { JwtModule } from '@auth0/angular-jwt';
import { provideAnimations } from '@angular/platform-browser/animations';
import { ApolloLink, InMemoryCache } from '@apollo/client/core';
import { setContext } from '@apollo/client/link/context';
import { provideApollo } from 'apollo-angular';
import { HttpLink } from 'apollo-angular/http';
import { LoginService } from './login/login.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideHttpClient(withInterceptors([AuthInterceptor])),
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    provideAnimationsAsync(),
    importProvidersFrom(JwtModule.forRoot({})),
    provideAnimations(),
    provideApollo(() => {
      const httpLink = inject(HttpLink);
      const loginService = inject(LoginService);

      const basic = setContext((operation, context) => ({
        headers: {
          Accept: 'charset=utf-8',
        },
      }));

      const auth = setContext((operation, context) => {
        const token = loginService.getToken();

        if (token === null) {
          return {};
        } else {
          return {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          };
        }
      });

      return {
        link: ApolloLink.from([
          basic,
          auth,
          httpLink.create({
            uri: 'https://smarthomegraphql-intelligent-jaguar-pd.apps.01.cf.eu01.stackit.cloud/graphql',
          }),
        ]),
        cache: new InMemoryCache(),
        // other options ...
      };
    }),
  ],
};
