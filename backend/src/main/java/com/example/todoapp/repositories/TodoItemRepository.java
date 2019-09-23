package com.example.todoapp.repositories;

import com.example.todoapp.models.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends MongoRepository<TodoItem, String> {
    public List<TodoItem> findByTodolist(String todolist);
}
