import { Component } from '@angular/core';
import { Auth } from '../../model/auth';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  imports: [ FormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
  protected formAuth: Auth = { username: "", password: "" };

  constructor(private authService: AuthService, private router: Router) { }

  protected login() {
    this.authService.login(this.formAuth).subscribe(resp => {
      // On affecte le jeton JWT reçu au service
      this.authService.token = resp.token;

      // Stockage du jeton dans la session du navigateur
      sessionStorage.setItem('token', resp.token);

      // On redirige vers la page de la liste des matières
      this.router.navigate([ 'matiere-v2' ]);
    });
  }
}
