package com.example.todoapp.api;

import javax.validation.Valid;
import com.example.todoapp.models.TodoItem;
import com.example.todoapp.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoItemController {

    @Autowired
    TodoItemRepository todoItemRepository;

    @GetMapping("/todos")
    public List<TodoItem> getAllTodos() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return todoItemRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todos")
    public TodoItem createTodo(@Valid @RequestBody TodoItem todoItem) {
        todoItem.setCompleted(false);
        return todoItemRepository.save(todoItem);
    }

    @GetMapping(value="/todos/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable("id") String id) {
        return todoItemRepository.findById(id)
                .map(todoItem -> ResponseEntity.ok().body(todoItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/todos/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable("id") String id,
                                               @Valid @RequestBody TodoItem todoItem) {
        return todoItemRepository.findById(id)
                .map(todoItemData -> {
                    todoItemData.setText(todoItem.getText());
                    todoItemData.setCompleted(todoItem.getCompleted());
                    TodoItem updatedTodoItem = todoItemRepository.save(todoItemData);
                    return ResponseEntity.ok().body(updatedTodoItem);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value="/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return todoItemRepository.findById(id)
                .map(todoItem -> {
                    todoItemRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
