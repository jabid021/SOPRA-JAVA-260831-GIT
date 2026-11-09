import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AjcTextField } from '../../../component/form';
import { AuthService } from '../../../service/auth-service';

@Component({
  imports: [ ReactiveFormsModule, AjcTextField, RouterLink ],
  templateUrl: './login-page.html',
  styleUrl: './login-page.css',
})
export class LoginPage implements OnInit {
  private authService: AuthService = inject(AuthService);
  private formBuilder: FormBuilder = inject(FormBuilder);
  private router: Router = inject(Router);

  protected loginError = signal(false);
  protected userForm!: FormGroup;
  protected usernameCtrl!: FormControl;
  protected passwordCtrl!: FormControl;

  protected passwordErrorMessages = {
    required: 'Le mot de passe est obligatoire',
    minlength: 'Le mot de passe doit faire 6 caractères minimum'
  };

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);

    this.userForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl
    });
  }

  public async connecter() {
    this.loginError.set(false);
    this.authService.auth(this.userForm.getRawValue()).subscribe({
      // next => si la réponse est OK
      next: resp => {
        if (resp.success == false) {
          this.loginError.set(true);
          return;
        }

        this.authService.token = resp.token;
        this.router.navigate([ '/livre' ]);
      },

      // error => si la réponse est KO (30X, 40X, 50X)
      error: () => this.loginError.set(true)
    });
  }
}
