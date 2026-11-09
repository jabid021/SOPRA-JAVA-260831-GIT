import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { AjcModal } from '../../../component/ajc-modal/ajc-modal';
import { AjcSubmit, AjcTextField } from '../../../component/form';
import { CollectionDto } from '../../../dto/collection-dto';
import { CollectionService } from '../../../service/collection-service';

@Component({
  imports: [
    CommonModule, RouterLink,
    AjcTextField, AjcSubmit,
    AjcModal
  ],
  templateUrl: './collection-page.html',
  styleUrl: './collection-page.css',
})
export class CollectionPage implements OnInit {
  private collectionService: CollectionService = inject(CollectionService);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected collections$!: Observable<CollectionDto[]>;
  private refresh$: Subject<void> = new Subject<void>();

  protected showForm: boolean = false;
  protected collectionForm!: FormGroup;
  protected nameCtrl!: FormControl;
  protected editingCollection!: CollectionDto | null;

  ngOnInit(): void {
    this.collections$ = this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.collectionService.findAll();
      })
    );

    this.nameCtrl = this.formBuilder.control('', Validators.required);

    this.collectionForm = this.formBuilder.group({
      nom: this.nameCtrl
    });
  }

  public trackCollection(index: number, value: CollectionDto) {
    return value.id;
  }

  private reload() {
    this.refresh$.next();
  }

  public creerOuModifier() {
    const collection: CollectionDto = {
      ...this.collectionForm.getRawValue(),
      id: this.editingCollection?.id
    };

    this.collectionService.save(collection).subscribe(() => {
      this.showForm = false;
      this.editingCollection = null;
      this.collectionForm.reset();

      this.reload();
    });
  }

  public editer(collection: CollectionDto) {
    this.editingCollection = collection;
    this.showForm = true;

    this.nameCtrl.setValue(collection.nom);
  }

  public annulerEditer() {
    this.editingCollection = null;
    this.collectionForm.reset();
    this.showForm = false;
  }

  public supprimer(collection: CollectionDto) {
    this.collectionService.deleteById(collection.id).subscribe(() => this.reload());
  }
}
