import { Injectable } from '@angular/core';
import { Todo } from '../model/todo';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TodoService {
  constructor(private http: HttpClient) { }

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

  public findAll(): Observable<Todo[]> {
    return this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos');
  }

  public add(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>('https://jsonplaceholder.typicode.com/todos', todo);
  }

  public remove(todo: Todo): Observable<void> {
    return this.http.delete<void>(`https://jsonplaceholder.typicode.com/todos/${ todo.id }`);
  }
}
