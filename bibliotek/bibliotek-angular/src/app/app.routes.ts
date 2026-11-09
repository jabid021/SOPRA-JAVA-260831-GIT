import { Routes } from '@angular/router';
import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/utilisateur/login-page/login-page';
import { LivrePage } from './page/livre/livre-page/livre-page';
import { CollectionPage } from './page/collection/collection-page/collection-page';
import { EditeurPage } from './page/editeur/editeur-page/editeur-page';
import { authGuard } from './guard/auth-guard';
import { InscriptionPage } from './page/utilisateur/inscription-page/inscription-page';
import { AvisPage } from './page/avis/avis-page/avis-page';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPage,
        title: "Connexion"
    },

    {
        path: 'inscription',
        component: InscriptionPage,
        title: "Inscription"
    },

    {
        path: 'home',
        title: "Accueil",
        component: HomePage,
        canActivate: [ authGuard ]
    },

    {
        path: 'livre',
        title: "Liste des livres",
        component: LivrePage,
        canActivate: [ authGuard ]
    },

    {
        path: 'auteur',
        title: "Liste des auteurs",
        component: AuteurPage,
        canActivate: [ authGuard ]
    },

    {
        path: 'editeur',
        title: "Liste des éditeurs",
        component: EditeurPage,
        canActivate: [ authGuard ]
    },

    {
        path: 'collection',
        title: "Liste des collections",
        component: CollectionPage,
        canActivate: [ authGuard ]
    },

    {
        path: 'avis',
        title: "Liste des avis",
        component: AvisPage,
        canActivate: [ authGuard ]
    },

    { path: '', redirectTo: 'home', pathMatch: 'full' }
];
