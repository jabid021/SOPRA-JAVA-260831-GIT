import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('PREMIERE APP');
  protected prenom: string = "Jeremy";

  protected modifierPrenom() {
    this.prenom = "Nouveau prénom";
  }

  protected onInput(event: any) {
    this.prenom = event.target.value;
  }
}
