import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Auth } from '../../model/auth';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-login-page',
  imports: [ ReactiveFormsModule ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  protected formLoginGroup!: FormGroup;
  protected formLoginUsernameCtrl!: FormControl;
  protected formLoginPasswordCtrl!: FormControl;

  constructor(
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.formLoginUsernameCtrl = this.formBuilder.control("", Validators.required);
    this.formLoginPasswordCtrl = this.formBuilder.control("", [ Validators.required, Validators.minLength(4) ]);

    this.formLoginGroup = this.formBuilder.group({
      username: this.formLoginUsernameCtrl,
      password: this.formLoginPasswordCtrl
    });
  }

  protected login() {
    this.authService.login(this.formLoginGroup.getRawValue()).subscribe(resp => {
      // On affecte le jeton JWT reçu au service
      this.authService.token = resp.token;

      // Stockage du jeton dans la session du navigateur
      sessionStorage.setItem('token', resp.token);

      // On redirige vers la page de la liste des matières
      this.router.navigate([ 'matiere-v2' ]);
    });
  }
}
