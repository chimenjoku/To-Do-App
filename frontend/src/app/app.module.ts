import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { TodoService } from './services/todo.service';
import { TodoListSelectorComponent } from './components/todo-list-selector/todo-list-selector.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { Router, RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {
    path: 'lists',
    component: TodoListSelectorComponent
  },
  {
    path: '',
    component: TodoListSelectorComponent,
    pathMatch: 'full'
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];
@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    TodoListSelectorComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes, { enableTracing: true })
  ],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
