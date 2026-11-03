import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const demoGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);

  console.log("GUARD ACTIF !!!");

  // On autorise l'accès
  // return true;

  // return true / false;

  // On autorise pas l'accès
  return router.createUrlTree([ 'login' ]);
};
