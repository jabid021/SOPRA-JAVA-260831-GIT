import { Routes } from '@angular/router';
import { authGuard } from './guard/auth-guard';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/login-page/login-page';
import { MatierePage } from './page/matiere-page/matiere-page';
import { MatiereV2Page } from './page/matiere-v2-page/matiere-v2-page';

export const routes: Routes = [
  // Page de connexion
  {
    path: 'login',
    component: LoginPage
  },

  // Page d'accueil
  {
    path: 'home',
    component: HomePage,
    canActivate: [ authGuard ]
  },

  // Page Matières
  {
    path: 'matiere',
    component: MatierePage,
    canActivate: [ authGuard ]
  },

  // Page Matières V2
  {
    path: 'matiere-v2',
    component: MatiereV2Page,
    canActivate: [ authGuard ]
  },

  // Rediriger par défaut sur l'accueil
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
