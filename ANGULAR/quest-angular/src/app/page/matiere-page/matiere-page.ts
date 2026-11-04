import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere-service';

@Component({
  selector: 'app-matiere-page',
  imports: [ ReactiveFormsModule, AsyncPipe ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  protected matieres$!: Observable<Matiere[]>;
  private refresh$: Subject<void> = new Subject<void>(); // Permettra de garder la même référence à l'Observable et alimenter le flux dès qu'un changement sera réalisé

  protected editingMatiereId?: number;
  protected formMatiereGroup!: FormGroup;
  protected formMatiereLibelleCtrl!: FormControl

  constructor(
    private title: Title,
    private matiereService: MatiereService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.title.setTitle("Liste des matières");
    // this.matieres$ = this.matiereService.findAll();

    // Création du formulaire ReactiveForms
    this.formMatiereLibelleCtrl = this.formBuilder.control("", Validators.required);

    this.formMatiereGroup = this.formBuilder.group({
      libelle: this.formMatiereLibelleCtrl
    });

    this.matieres$ = this.refresh$.pipe(
      // On initialise notre Observable
      startWith(null),

      // Fonction qui sera déclenchée dès qu'une nouvelle valeur dans le flux arrivera
      switchMap(() => {
        return this.matiereService.findAll();
      })
    );
  }

  protected ajouterOuModifier() {
    if (this.formMatiereGroup.invalid) {
      return;
    }

    // const matiere: Matiere = {
    //   libelle: this.formMatiereLibelleCtrl.value
    // };

    const matiere: Matiere = {
      ...this.formMatiereGroup.getRawValue(),
      id: this.editingMatiereId
    };

    this.matiereService.save(matiere).subscribe(() => {
      // this.matieres$ = this.matiereService.findAll();
      this.refresh$.next();
    });

    this.formMatiereGroup.reset();
  }

  protected editer(matiere: Matiere) {
    this.editingMatiereId = matiere.id;
    this.formMatiereLibelleCtrl.setValue(matiere.libelle);
  }

  protected supprimer(matiere: Matiere) {
    this.matiereService.remove(matiere).subscribe(() => {
      // this.matieres$ = this.matiereService.findAll();
      this.refresh$.next();
    });
  }

  protected annulerEdition() {
    this.editingMatiereId = undefined;
    this.formMatiereGroup.reset();
  }
}
