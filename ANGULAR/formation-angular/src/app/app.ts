import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Todo } from './todo';

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

  protected todos: Todo[] = [
    { id: 1, title: "Faire du café", completed: true },
    { id: 2, title: "Boire le café", completed: false }
  ];

  protected formTodo: Todo = { id: 0, title: "", completed: false };

  protected modifierPrenom() {
    this.prenom = "Nouveau prénom";
  }

  protected onInput(event: any) {
    this.prenom = event.target.value;
  }

  protected ajouterTodo() {
    this.todos.push(this.formTodo);
    this.formTodo = { id: 0, title: "", completed: false };

    // this.todos.push({
    //   id: this.formTodo.id,
    //   title: this.formTodo.title,
    //   completed: this.formTodo.completed
    // });

    // this.todos.push({ ...this.formTodo });
  }
}
