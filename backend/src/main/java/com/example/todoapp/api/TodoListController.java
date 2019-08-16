package com.example.todoapp.api;

import javax.validation.Valid;
import com.example.todoapp.models.TodoList;
import com.example.todoapp.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoListController {

    @Autowired
    TodoListRepository todoListRepository;

    @GetMapping("/todolists")
    public List<TodoList> getAllLists() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoListRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todolists")
    public TodoList createTodoList(@Valid @RequestBody TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @GetMapping(value="/todolists/{id}")
    public ResponseEntity<TodoList> getTodoById(@PathVariable("id") String id) {
        return todoListRepository.findById(id)
                .map(todoItem -> ResponseEntity.ok().body(todoItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/todolists/{id}")
    public ResponseEntity<TodoList> updateTodo(@PathVariable("id") String id,
                                               @Valid @RequestBody TodoList todoList) {
        return todoListRepository.findById(id)
                .map(todoListData -> {
                    TodoList updatedTodoList = todoListRepository.save(todoListData);
                    return ResponseEntity.ok().body(updatedTodoList);
                }).orElse(ResponseEntity.notFound().build());
    }
//TODO: Make deletion cascade todo items
    @DeleteMapping(value="/todolists/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return todoListRepository.findById(id)
                .map(todoList -> {
                    todoListRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
