import { Component } from '@angular/core';
import { Auth } from '../../model/auth';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ FormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage {
  protected formAuth: Auth = { username: "", password: "" };

  constructor(private authService: AuthService) { }

  protected login() {
    this.authService.login(this.formAuth).subscribe(resp => {
      // On affecte le jeton JWT reçu au service
      this.authService.token = resp.token;
    });
  }
}
