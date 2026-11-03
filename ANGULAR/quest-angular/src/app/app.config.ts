import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { jwtInterceptor } from './interceptor/jwt-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),

    // Configuration du client HTTP
    provideHttpClient(
      // Avec les intercepteurs ...
      withInterceptors([
        // demoInterceptor, // DemoInterceptor
        jwtInterceptor // JWT Interceptor
      ])
    )
  ]
};
