package com.example.todoapp.repositories;

import com.example.todoapp.models.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends MongoRepository<TodoList, String> {

}

