import { Component, inject, OnInit } from '@angular/core';
import { FiliereService } from '../../service/filiere-service';
import { Observable } from 'rxjs';
import { Filiere } from '../../model/filiere';
import { AsyncPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-filiere-page',
  imports: [ AsyncPipe, DatePipe ],
  templateUrl: './filiere-page.html',
  styleUrl: './filiere-page.css',
})
export class FilierePage implements OnInit {
  private filiereService: FiliereService = inject(FiliereService);
  protected filieres$!: Observable<Filiere[]>;

  ngOnInit(): void {
    this.filieres$ = this.filiereService.findAll();
  }
}
