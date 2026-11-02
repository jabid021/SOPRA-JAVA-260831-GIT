import { Injectable } from '@angular/core';
import { Todo } from '../model/todo';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private todoId: number = 3;

  private todos: Todo[] = [
    { id: 1, title: "Faire du café (2)", completed: true },
    { id: 2, title: "Boire le café", completed: false }
  ];

  public findAll(): Todo[] {
    return this.todos;
  }

  public add(todo: Todo) {
    todo.id = this.todoId++;

    this.todos.push(todo);
  }

  public remove(todo: Todo) {
    const index = this.todos.indexOf(todo);
    this.todos.splice(index, 1);
  }
}
