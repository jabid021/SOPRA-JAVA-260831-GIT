import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AjcModal } from '../../../component/ajc-modal/ajc-modal';
import { AjcRating } from '../../../component/ajc-rating/ajc-rating';
import { AjcFormField, AjcSelect, AjcSubmit, AjcTextField } from '../../../component/form';
import { AvisDto } from '../../../dto/avis-dto';
import { LivreDto } from '../../../dto/livre-dto';
import { AvisService } from '../../../service/avis-service';
import { LivreService } from '../../../service/livre-service';

@Component({
  imports: [
    CommonModule,
    AjcFormField, AjcTextField, AjcSelect, AjcSubmit,
    AjcModal, AjcRating
  ],
  templateUrl: './avis-page.html',
  styleUrl: './avis-page.css',
})
export class AvisPage implements OnInit {
  private avisService: AvisService = inject(AvisService);
  private livreService: LivreService = inject(LivreService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected avis$!: Observable<AvisDto[]>;
  private refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<LivreDto[]>;

  protected showForm: boolean = false;
  protected avisForm!: FormGroup;
  protected noteCtrl!: FormControl;
  protected commentaireCtrl!: FormControl;
  protected livreCtrl!: FormControl;
  protected editingAvis!: AvisDto | null;

  ngOnInit(): void {
    this.avis$ = this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.avisService.findAll();
      })
    );

    this.livres$ = this.livreService.findAll();

    this.noteCtrl = this.formBuilder.control('', [ Validators.required, Validators.min(1), Validators.max(5) ]);
    this.commentaireCtrl = this.formBuilder.control('', Validators.required);
    this.livreCtrl = this.formBuilder.control('', Validators.required);

    this.avisForm = this.formBuilder.group({
      note: this.noteCtrl,
      commentaire: this.commentaireCtrl,
      livreId: this.livreCtrl
    });
  }

  public trackAvis(index: number, value: AvisDto) {
    return value.id;
  }

  private reload() {
    this.refresh$.next();
  }

  public creerOuModifier() {
    const avis: AvisDto = {
      ...this.avisForm.getRawValue(),
      id: this.editingAvis?.id
    };

    this.avisService.save(avis).subscribe(() => {
      this.showForm = false;
      this.editingAvis = null;
      this.avisForm.reset();

      this.reload();
    });
  }

  public editer(avis: AvisDto) {
    this.editingAvis = avis;
    this.showForm = true;

    this.noteCtrl.setValue(avis.note);
    this.commentaireCtrl.setValue(avis.commentaire);
    this.livreCtrl.setValue(avis.livreId);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingAvis = null;
    this.avisForm.reset();
  }

  public supprimer(avis: AvisDto) {
    this.avisService.deleteById(avis.id).subscribe(() => this.reload());
  }
}
