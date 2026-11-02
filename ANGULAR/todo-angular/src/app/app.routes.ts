import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { TodoListPage } from './page/todo-list-page/todo-list-page';

export const routes: Routes = [

  // Page d'accueil
  {
    path: 'home',
    component: HomePage,
  },

  // Page TodoListe
  {
    path: 'todo-list',
    component: TodoListPage
  },

  // Rediriger par défaut sur l'accueil
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
