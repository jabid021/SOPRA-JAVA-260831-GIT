import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth-service';
import { catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

export const jwtHeaderInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Pas de jeton, donc pas besoin de l'injecter ...
  if (!authService.token) {
    return next(req);
  }

  // API de connexion, donc pas besoin du jeton ...
  if (req.url.endsWith('/api/auth')) {
    return next(req);
  }

  // Dans les autres cas, on injecte le JWT
  const authReq = req.clone({
    setHeaders: {
      'Authorization': `Bearer ${ authService.token }`
    }
  });

  return next(authReq).pipe(
    catchError((error: HttpErrorResponse) => {

    // Si erreur d'autorisations, retour à la page login
    if (error.status === 403) {
      authService.resetAuth();
      router.navigate([ '/login' ]);
    }

    return throwError(() => error);
  })
);
};
