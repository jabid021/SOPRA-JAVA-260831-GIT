import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Todo } from '../../model/todo';
import { TodoCompletedPipe } from '../../pipe/todo-completed-pipe';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-todo-list-page',
  imports: [
    CommonModule, // Parce qu'on utilise ngIf ou ngFor
    FormsModule, // Parce qu'on utilise ngModel dans le template
    TodoCompletedPipe
  ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  protected title: Title = inject(Title);

  protected todos: Todo[] = [
    { id: 1, title: "Faire du café", completed: true },
    { id: 2, title: "Boire le café", completed: false }
  ];

  protected formTodo: Todo = { id: 0, title: "", completed: false };
  protected todoId: number = 3;

  ngOnInit(): void {
    this.title.setTitle("Liste des todos");
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
