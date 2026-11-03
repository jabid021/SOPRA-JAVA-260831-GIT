import { AsyncPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Matiere } from '../../model/matiere';
import { MatiereService } from '../../service/matiere-service';
import { rxResource } from '@angular/core/rxjs-interop';

@Component({
  selector: 'app-matiere-v2-page',
  imports: [ FormsModule, AsyncPipe ],
  templateUrl: './matiere-v2-page.html',
  styleUrl: './matiere-v2-page.css',
})
export class MatiereV2Page implements OnInit {
  protected matieres = rxResource({
    stream: () => this.matiereService.findAll()
  });
  protected formMatiere!: Matiere;

  constructor(private title: Title, private matiereService: MatiereService) {
    this.resetFormMatiere();
  }

  ngOnInit(): void {
    this.title.setTitle("Liste des matières");
  }

  protected ajouterOuModifier() {
    this.matiereService.save(this.formMatiere).subscribe(() => {
      this.matieres.reload();
    });

    this.resetFormMatiere();
  }

  protected editer(matiere: Matiere) {
    this.formMatiere = { ...matiere };
  }

  protected supprimer(matiere: Matiere) {
    this.matiereService.remove(matiere).subscribe(() => {
      this.matieres.reload();
    });
  }

  protected annulerEdition() {
    this.resetFormMatiere();
  }

  private resetFormMatiere() {
    this.formMatiere = { libelle: "" };
  }
}
