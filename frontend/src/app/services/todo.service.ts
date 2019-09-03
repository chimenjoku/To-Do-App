import { Injectable } from '@angular/core';
import { Todo } from '../models/todo';
import { Todolist } from '../models/todolist';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  // tslint:disable-next-line: quotemark
  private baseUrl = "http://localhost:8080/api";
  private  TODOLISTS_URL = `${this.baseUrl}/todolists`;
  private  TODOS_URL = `${this.baseUrl}/todos`;

  constructor(private http: HttpClient) { }

  getTodolists(): Observable<Todolist[]> {
    return this.http.get<Todolist[]>(this.TODOLISTS_URL);
  }

  getTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(this.TODOS_URL);
  }

  postTodolist(todolistData: Todolist): Observable<Todolist> {
    return this.http.post<Todolist>(this.TODOLISTS_URL, todolistData);

  }

  deleteTodolist(id: string): Observable<any> {
    return this.http.delete(this.TODOLISTS_URL + '/' + id);

  }

// TODO: Add capability to retrieve every todo in a todo list
  // getTodosByTodolist(todolistId: string): Observable<Todo[]> {
  //   return this.http.get<Todo[]>(this.NOTES_BY_NOTEBOOK_URL + notebookId);
  // }

  saveTodo(todoData: Todo): Observable<Todo> {
    return this.http.post<Todo>(this.TODOS_URL, todoData);
  }

  updateTodo(todoData: Todo): Observable<Todo> {
    return this.http.put<Todo>(this.TODOS_URL + '/' + todoData.id, todoData);
  }

  deleteTodo(todoData: Todo): Observable<Todo> {
    return this.http.delete<Todo>(this.TODOS_URL + '/' + todoData.id);
  }

}
