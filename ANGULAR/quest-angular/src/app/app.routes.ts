import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { MatierePage } from './page/matiere-page/matiere-page';
import { MatiereV2Page } from './page/matiere-v2-page/matiere-v2-page';
import { LoginPage } from './page/login-page/login-page';

export const routes: Routes = [
  // Page de connexion
  {
    path: 'login',
    component: LoginPage,
  },

  // Page d'accueil
  {
    path: 'home',
    component: HomePage,
  },

  // Page Matières
  {
    path: 'matiere',
    component: MatierePage
  },

  // Page Matières V2
  {
    path: 'matiere-v2',
    component: MatiereV2Page
  },

  // Rediriger par défaut sur l'accueil
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
