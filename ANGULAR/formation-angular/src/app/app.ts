import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { Todo } from './todo';
import { MonPremierComposant } from './mon-premier-composant/mon-premier-composant';
import { ListePrenoms } from './liste-prenoms/liste-prenoms';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule, // Parce qu'on utilise ngModel dans le template
    CommonModule, // Parce qu'on utilise ngIf ou ngFor
    MonPremierComposant, // Parce qu'on utilise ce composant
    ListePrenoms
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
  protected todoId: number = 3;

  protected modifierPrenom() {
    this.prenom = "Nouveau prénom";
  }

  protected onInput(event: any) {
    this.prenom = event.target.value;
  }

  protected ajouterOuModifierTodo() {
    if (!this.formTodo.id) {
      this.todos.push({
        ...this.formTodo,
        id: this.todoId++
      });
    }

    this.formTodo = { id: 0, title: "", completed: false };

    // this.todos.push({
    //   id: this.formTodo.id,
    //   title: this.formTodo.title,
    //   completed: this.formTodo.completed
    // });

    // this.todos.push({ ...this.formTodo });
  }

  protected editerTodo(todo: Todo) {
    this.formTodo = todo;
  }

  protected annulerEditerTodo() {
    this.formTodo = { id: 0, title: "", completed: false };
  }

  protected supprimerTodo(todo: Todo) {
    const index = this.todos.indexOf(todo);
    this.todos.splice(index, 1);
  }
}
