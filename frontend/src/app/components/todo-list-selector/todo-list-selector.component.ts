import { Component, OnInit} from '@angular/core';
import { NgForm } from '@angular/forms';
import { TodoService } from '../../services/todo.service';
import { Todolist } from '../../models/todolist';
import { Todo } from '../../models/todo';
import { getLocaleTimeFormat } from '@angular/common';

@Component({
  selector: 'app-todo-list-selector',
  templateUrl: './todo-list-selector.component.html',
  styleUrls: ['./todo-list-selector.component.css']
})
export class TodoListSelectorComponent implements OnInit {
  todolists: Todolist[] = [];
  todos: Todo[] = [];
  selectedTodolist: Todolist;
  visibleTodo: Todo = new Todo();
  editing = false;
  editingTodo: Todo = new Todo();


  constructor(private todoService: TodoService ) { }

  ngOnInit() {
    this.getAllTodolists();
    this.getAllTodos();
  }

  getAllTodolists() {
    this.todoService.getTodolists().subscribe(
      res => {
        this.todolists = res;
      },
      err => {
        alert('An error has occurred while downloading Todolists;');
      }
    );
  }

  getAllTodos() {
    this.todoService.getTodos().subscribe(
      res => {
        this.todos = res;
      },
      err => { alert('Error occurred while downloading the todos;'); }
    );
  }

  createTodolist() {
    // tslint:disable-next-line: prefer-const
    let newTodolist: Todolist = {
      title : 'New To-do list',
      id: null,
      createdAt: null
    };

    this.todoService.postTodolist(newTodolist).subscribe(
      res => {
        newTodolist.id = res.id;
        this.todolists.push(newTodolist);
      },
      err => { alert('An error has occurred while saving the notebook'); }
    );
  }

  updateTodolist(updatedTodoList: Todolist) {
    this.todoService.postTodolist(updatedTodoList).subscribe(
      res => {
      },
      err => { alert('An error has occurred while updating the Todolist'); }
    );
  }

  deleteTodolist(todolist: Todolist) {
    if (confirm('Are you sure you want to delete notebook?')) {
      this.todoService.deleteTodolist(todolist.id).subscribe(
        res => {
          // tslint:disable-next-line: prefer-const
          this.todolists = this.todolists.filter(t => t.id !== todolist.id);
          // let indexOfTodolist = this.todolists.indexOf(todolist);
          // this.todolists.splice(indexOfTodolist, 1);
        },
        err => {
          alert('Could not delete notebook');
        }
      );
    }
  }

  selectTodolist(todolist: Todolist) {
    this.selectedTodolist = todolist;
    this.todoService.getTodos().subscribe(
      res => {
        this.todos = res;
      },
      err => { alert('An error has occurred while downloading the notes;'); }
    );
  }
  createTodo(todoForm: NgForm, selected: Todolist) {
    // tslint:disable-next-line: prefer-const
    let newTodo: Todo = {
      id: null,
      text: null,
      todolist: selected.title,
      completed: null,
      createdAt: null
    };

    this.todoService.saveTodo(newTodo).subscribe(
      res => {
        newTodo.id = res.id;
        this.todos.push(newTodo);
      },
      err => { alert('An error has occurred while saving the todo item'); }
    );

  }


  deleteTodo(todoData: Todo): void {
    if (confirm('Are you sure you want to delete To-do Item?')) {

      // Remove from server and UI
      this.todoService.deleteTodo(todoData).subscribe(res => {
        this.todos = this.todos.filter(t => t.id !== todoData.id);
      },
        err => {
          alert('Could not delete Todo');
        }
      );
    }
  }

  updateTodo(todoData: Todo): void {
    this.todoService.updateTodo(todoData).subscribe(updatedTodo => {
      const existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
      Object.assign(existingTodo, updatedTodo);
      this.clearEditing();
    });
  }

  toggleCompleted(todoData: Todo): void {
    todoData.completed = !todoData.completed;
    this.todoService.updateTodo(todoData)
      .subscribe(updatedTodo => {
        const existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
        Object.assign(existingTodo, updatedTodo);
      });
  }

  editTodo(todoData: Todo): void {
    this.editing = true;
    Object.assign(this.editingTodo, todoData);
  }

  clearEditing(): void {
    this.editingTodo = new Todo();
    this.editing = false;
  }
}
