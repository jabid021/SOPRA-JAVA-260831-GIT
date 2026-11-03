import { AsyncPipe, CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Todo } from '../../model/todo';
import { TodoCompletedPipe } from '../../pipe/todo-completed-pipe';
import { Title } from '@angular/platform-browser';
import { TodoService } from '../../service/todo-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-todo-list-page',
  imports: [
    CommonModule, // Parce qu'on utilise ngIf ou ngFor
    FormsModule, // Parce qu'on utilise ngModel dans le template
    TodoCompletedPipe,
    AsyncPipe
  ],
  templateUrl: './todo-list-page.html',
  styleUrl: './todo-list-page.css',
})
export class TodoListPage implements OnInit {
  protected title: Title = inject(Title);
  protected todoService: TodoService = inject(TodoService);

  protected todos$!: Observable<Todo[]>;

  protected formTodo: Todo = { id: 0, title: "", completed: false };

  ngOnInit(): void {
    this.title.setTitle("Liste des todos");

    this.todos$ = this.todoService.findAll();
  }

  protected ajouterOuModifierTodo() {
    if (!this.formTodo.id) {
      this.todoService.add(this.formTodo).subscribe();
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
    this.todoService.remove(todo).subscribe();
  }
}
