import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AjcTextField } from '../../../component/form';
import { UtilisateurService } from '../../../service/utilisateur-service';
import { passwordMatchValidator } from '../../../validator/password-match-validator';

@Component({
  imports: [ CommonModule, ReactiveFormsModule, AjcTextField, RouterLink ],
  templateUrl: './inscription-page.html',
  styleUrl: './inscription-page.css',
})
export class InscriptionPage implements OnInit {
  private utilisateurService: UtilisateurService = inject(UtilisateurService);
  private formBuilder: FormBuilder = inject(FormBuilder);
  private router: Router = inject(Router);

  protected subscriptionError: boolean = false;

  protected userForm!: FormGroup;
  protected usernameCtrl!: FormControl;
  protected passwordCtrl!: FormControl;
  protected passwordConfirmCtrl!: FormControl;

  protected passwordErrorMessages = {
    required: "Le mot de passe est obligatoire",
    minlength: "Le mot de passe doit faire 6 caractères minimum"
  };

  protected passwordConfirmErrorMessages = {
    passwordMismatch: "Les mots de passe ne correspondent pas"
  };

  ngOnInit(): void {
    this.usernameCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);
    this.passwordConfirmCtrl = this.formBuilder.control('', [ Validators.required, Validators.minLength(6) ]);

    this.userForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl,
      passwordConfirm: this.passwordConfirmCtrl
    }, {
      validators: passwordMatchValidator('password', 'passwordConfirm')
    });
  }

  public async connecter() {
    try {
      await this.utilisateurService.subscribe(this.userForm.getRawValue());

      this.router.navigate([ '/login' ]);
    }

    // Si la connexion n'a pas pu se faire, affichage de l'erreur sur le template
    catch {
      this.subscriptionError = true;
    }
  }
}
