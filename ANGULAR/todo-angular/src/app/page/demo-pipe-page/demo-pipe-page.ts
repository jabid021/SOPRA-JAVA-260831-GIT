import { Component, inject, OnInit } from '@angular/core';
import { PremierPipe } from '../../pipe/premier-pipe';
import { UpperCasePipe } from '@angular/common';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-demo-pipe-page',
  imports: [
    PremierPipe,
    UpperCasePipe
  ],
  templateUrl: './demo-pipe-page.html',
  styleUrl: './demo-pipe-page.css',
})
export class DemoPipePage implements OnInit {
  protected varBoolean: boolean = false;

  // Injection par fonction "inject"
  private title: Title = inject(Title);

  // Injection par constructeur
  // constructor(private title: Title) {
  //   this.title.setTitle("Démonstration de Pipe");
  // }

  // Cette méthode s'exécute à l'initialisation du composant
  ngOnInit(): void {
    this.title.setTitle("Démonstration de Pipe");
  }
}
