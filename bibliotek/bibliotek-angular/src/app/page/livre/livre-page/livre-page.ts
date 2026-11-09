import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AjcModal } from '../../../component/ajc-modal/ajc-modal';
import { AjcSelect, AjcSubmit, AjcTextField } from '../../../component/form';
import { AuteurDto } from '../../../dto/auteur-dto';
import { CollectionDto } from '../../../dto/collection-dto';
import { CreateOrUpdateLivreDto } from '../../../dto/create-or-update-livre-dto';
import { EditeurDto } from '../../../dto/editeur-dto';
import { LivreDto } from '../../../dto/livre-dto';
import { AuteurService } from '../../../service/auteur-service';
import { CollectionService } from '../../../service/collection-service';
import { EditeurService } from '../../../service/editeur-service';
import { LivreService } from '../../../service/livre-service';

@Component({
  imports: [
    CommonModule, RouterLink,
    AjcTextField, AjcSelect, AjcSubmit,
    AjcModal
  ],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  private livreService: LivreService = inject(LivreService);
  private auteurService: AuteurService = inject(AuteurService);
  private editeurService: EditeurService = inject(EditeurService);
  private collectionService: CollectionService = inject(CollectionService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected livres$!: Observable<LivreDto[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected auteurs$!: Observable<AuteurDto[]>;
  protected editeurs$!: Observable<EditeurDto[]>;
  protected collections$!: Observable<CollectionDto[]>;

  protected showForm: boolean = false;
  protected livreForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected publicationCtrl!: FormControl;
  protected authorCtrl!: FormControl;
  protected editorCtrl!: FormControl;
  protected collectionCtrl!: FormControl;
  protected editingLivre!: LivreDto | null;

  protected publicationErrorMessages = {
    required: "La date de publication est obligatoire",
    pattern: "La date doit être au format année-mois-jour"
  };

  ngOnInit(): void {
    this.livres$ = this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.livreService.findAll();
      })
    );

    this.auteurs$ = this.auteurService.findAll();
    this.editeurs$ = this.editeurService.findAll();
    this.collections$ = this.collectionService.findAll();

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.publicationCtrl = this.formBuilder.control('', [ Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/) ]);
    this.authorCtrl = this.formBuilder.control('', Validators.required);
    this.editorCtrl = this.formBuilder.control('', Validators.required);
    this.collectionCtrl = this.formBuilder.control(null);

    this.livreForm = this.formBuilder.group({
      nom: this.nameCtrl,
      publication: this.publicationCtrl,
      auteurId: this.authorCtrl,
      editeurId: this.editorCtrl,
      collectionId: this.collectionCtrl
    });
  }

  public trackLivre(index: number, value: LivreDto) {
    return value.id;
  }

  private reload() {
    this.refresh$.next();
  }

  public creerOuModifier() {
    const livre: CreateOrUpdateLivreDto = {
      ...this.livreForm.getRawValue(),
      id: this.editingLivre?.id
    };

    this.livreService.save(livre).subscribe(() => {
      this.showForm = false;
      this.editingLivre = null;
      this.livreForm.reset();

      this.reload();
    });
  }

  public editer(livre: LivreDto) {
    this.editingLivre = livre;
    this.showForm = true;

    this.nameCtrl.setValue(livre.nom);
    this.publicationCtrl.setValue(livre.publication);
    this.authorCtrl.setValue(livre.auteurId);
    this.editorCtrl.setValue(livre.editeurId);
    this.collectionCtrl.setValue(livre.collectionId);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingLivre = null;
    this.livreForm.reset();
  }

  public supprimer(livre: LivreDto) {
    this.livreService.deleteById(livre.id).subscribe(() => this.reload());
  }
}
