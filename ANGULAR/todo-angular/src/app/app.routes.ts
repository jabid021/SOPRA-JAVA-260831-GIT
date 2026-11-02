import { Routes } from '@angular/router';
import { DemoPipePage } from './page/demo-pipe-page/demo-pipe-page';
import { HomePage } from './page/home-page/home-page';
import { TodoListPage } from './page/todo-list-page/todo-list-page';
import { DemoObservablePage } from './page/demo-observable-page/demo-observable-page';

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

  // Page Démo Pipe
  {
    path: 'demo-pipe',
    component: DemoPipePage
  },

  // Page Démo Observables
  {
    path: 'demo-observable',
    component: DemoObservablePage
  },

  // Rediriger par défaut sur l'accueil
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];
