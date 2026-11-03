import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../service/auth-service';
import { inject } from '@angular/core';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService: AuthService = inject(AuthService);

  // Si on cherche à s'authentifier, pas d'Authorization
  if (req.url.endsWith('/auth')) {
    return next(req);
  }

  // Cloner la requête HTTP et la modifier pour insérer le jeton JWT
  const request = req.clone({
    setHeaders: {
      'Authorization': `Bearer ${ authService.token }`
    }
  });

  return next(request);
};
