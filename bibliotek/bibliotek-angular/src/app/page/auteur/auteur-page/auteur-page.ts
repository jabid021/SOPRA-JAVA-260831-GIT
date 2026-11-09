import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AjcModal } from '../../../component/ajc-modal/ajc-modal';
import { AjcSelect, AjcSubmit, AjcTextField } from '../../../component/form';
import { AuteurDto } from '../../../dto/auteur-dto';
import { NATIONALITE_LABEL } from '../../../enumerator/nationalite-label';
import { NationalitePipe } from '../../../pipe/nationalite-pipe';
import { AuteurService } from '../../../service/auteur-service';

@Component({
  imports: [
    CommonModule, RouterLink,
    NationalitePipe,
    AjcTextField, AjcSelect, AjcSubmit,
    AjcModal
  ],
  templateUrl: './auteur-page.html',
  styleUrl: './auteur-page.css',
})
export class AuteurPage implements OnInit {
  private auteurService: AuteurService = inject(AuteurService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected auteurs$!: Observable<AuteurDto[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected showForm: boolean = false;
  protected auteurForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected firstnameCtrl!: FormControl;
  protected nationalityCtrl!: FormControl;
  protected nationalites = Object.keys(NATIONALITE_LABEL);
  protected editingAuteur!: AuteurDto | null;

  ngOnInit(): void {
    this.auteurs$ = this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.auteurService.findAll();
      })
    );

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.firstnameCtrl = this.formBuilder.control('', Validators.required);
    this.nationalityCtrl = this.formBuilder.control('', Validators.required);

    this.auteurForm = this.formBuilder.group({
      nom: this.nameCtrl,
      prenom: this.firstnameCtrl,
      nationalite: this.nationalityCtrl
    });
  }

  public trackAuteur(index: number, value: AuteurDto) {
    return value.id;
  }

  private reload() {
    this.refresh$.next();
  }

  public creerOuModifier() {
    const auteur: AuteurDto = {
      ...this.auteurForm.getRawValue(),
      id: this.editingAuteur?.id
    };

    this.auteurService.save(auteur).subscribe(() => {
      this.showForm = false;
      this.editingAuteur = null;
      this.auteurForm.reset();

      this.reload();
    });
  }

  public editer(auteur: AuteurDto) {
    this.editingAuteur = auteur;
    this.showForm = true;

    this.nameCtrl.setValue(auteur.nom);
    this.firstnameCtrl.setValue(auteur.prenom);
    this.nationalityCtrl.setValue(auteur.nationalite);
  }

  public annulerEditer() {
    this.editingAuteur = null;
    this.auteurForm.reset();
    this.showForm = false;
  }

  public supprimer(auteur: AuteurDto) {
    this.auteurService.deleteById(auteur.id).subscribe(() => this.reload());
  }
}
