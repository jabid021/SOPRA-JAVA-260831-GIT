import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AjcModal } from '../../../component/ajc-modal/ajc-modal';
import { AjcSelect, AjcSubmit, AjcTextField } from '../../../component/form';
import { EditeurDto } from '../../../dto/editeur-dto';
import { NATIONALITE_LABEL } from '../../../enumerator/nationalite-label';
import { NationalitePipe } from '../../../pipe/nationalite-pipe';
import { EditeurService } from '../../../service/editeur-service';

@Component({
  imports: [
    CommonModule, RouterLink,
    NationalitePipe,
    AjcTextField, AjcSelect, AjcSubmit,
    AjcModal
  ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  private editeurService: EditeurService = inject(EditeurService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected editeurs$!: Observable<EditeurDto[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected showForm: boolean = false;
  protected editeurForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected paysCtrl!: FormControl;
  protected nationalites = Object.keys(NATIONALITE_LABEL);
  protected editingEditeur!: EditeurDto | null;

  ngOnInit(): void {
    this.editeurs$ = this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.editeurService.findAll();
      })
    );

    this.nameCtrl = this.formBuilder.control('', Validators.required);
    this.paysCtrl = this.formBuilder.control('', Validators.required);

    this.editeurForm = this.formBuilder.group({
      nom: this.nameCtrl,
      pays: this.paysCtrl
    });
  }

  public trackEditeur(index: number, value: EditeurDto) {
    return value.id;
  }

  private reload() {
    this.refresh$.next();
  }

  public creerOuModifier() {
    const collection: EditeurDto = {
      ...this.editeurForm.getRawValue(),
      id: this.editingEditeur?.id
    };

    this.editeurService.save(collection).subscribe(() => {
      this.showForm = false;
      this.editingEditeur = null;
      this.editeurForm.reset();

      this.reload();
    });

  }

  public editer(editeur: EditeurDto) {
    this.editingEditeur = editeur;
    this.showForm = true;

    this.nameCtrl.setValue(editeur.nom);
    this.paysCtrl.setValue(editeur.pays);
  }

  public annulerEditer() {
    this.editingEditeur = null;
    this.editeurForm.reset();
    this.showForm = false;
  }

  public supprimer(editeur: EditeurDto) {
    this.editeurService.deleteById(editeur.id).subscribe(() => this.reload());
  }
}
