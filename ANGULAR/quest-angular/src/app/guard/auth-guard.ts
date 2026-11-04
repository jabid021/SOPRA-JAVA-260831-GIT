import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  if (authService.token) {
    return true;
  }

  return router.createUrlTree([ 'login' ]);
};
