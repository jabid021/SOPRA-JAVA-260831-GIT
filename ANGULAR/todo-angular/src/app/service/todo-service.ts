import { Injectable } from '@angular/core';
import { Todo } from '../model/todo';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  private todoId: number = 3;

  private todos: Todo[] = [
    { id: 1, title: "Faire du café (2)", completed: true },
    { id: 2, title: "Boire le café", completed: false }
  ];

  constructor(private http: HttpClient) {

  }

  public findAll(): Todo[] {
    return this.todos;
  }

  // public findAllObs(): void {
  //   const obs: Observable<Todo[]> = this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos');

  //   obs.subscribe(todos => {
  //     // console.log(todos);
  //     // this.todos = todos;

  //     for (let t of todos) {
  //       this.todos.push(t);
  //     }
  //   });
  // }

  public findAllObs(): Observable<Todo[]> {
    return this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos');
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
