import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere-service';

@Component({
  selector: 'app-matiere-page',
  imports: [ FormsModule, AsyncPipe ],
  templateUrl: './matiere-page.html',
  styleUrl: './matiere-page.css',
})
export class MatierePage implements OnInit {
  protected matieres$!: Observable<Matiere[]>;
  private refresh$: Subject<void> = new Subject<void>(); // Permettra de garder la même référence à l'Observable et alimenter le flux dès qu'un changement sera réalisé

  protected formMatiere!: Matiere;

  constructor(private title: Title, private matiereService: MatiereService) {
    this.resetFormMatiere();
  }

  ngOnInit(): void {
    this.title.setTitle("Liste des matières");
    // this.matieres$ = this.matiereService.findAll();

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
    this.matiereService.save(this.formMatiere).subscribe(() => {
      // this.matieres$ = this.matiereService.findAll();
      this.refresh$.next();
    });

    this.resetFormMatiere();
  }

  protected editer(matiere: Matiere) {
    this.formMatiere = { ...matiere };
  }

  protected supprimer(matiere: Matiere) {
    this.matiereService.remove(matiere).subscribe(() => {
      // this.matieres$ = this.matiereService.findAll();
      this.refresh$.next();
    });
  }

  protected annulerEdition() {
    this.resetFormMatiere();
  }

  private resetFormMatiere() {
    this.formMatiere = { libelle: "" };
  }
}
