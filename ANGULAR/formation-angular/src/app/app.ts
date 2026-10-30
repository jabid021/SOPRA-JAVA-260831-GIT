import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule, // Parce qu'on utilise ngModel dans le template
    CommonModule // Parce qu'on utilise ngIf ou ngFor
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('PREMIERE APP');
  protected prenom: string = "Jeremy";
  protected couleur: string = "black";

  protected prenoms: string[] = [ "Albert", "Bernard", "Test Cédric" ];

  protected modifierPrenom() {
    this.prenom = "Nouveau prénom";
  }

  protected onInput(event: any) {
    this.prenom = event.target.value;
  }
}
