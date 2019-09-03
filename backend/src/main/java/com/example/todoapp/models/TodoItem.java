package com.example.todoapp.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class TodoItem {
    @Id
    private String id;

    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String text;

    private String todolist;

    private Boolean completed = false;

    private Date createdAt = new Date();

    public TodoItem() {
        super();
    }

    public TodoItem(String title, String todolist) {
        this.text = text;
        this.todolist = todolist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTodolist() {
        return todolist;
    }

    public void getTodolist(String todolist) {
        this.todolist = todolist;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format(
                "TodoItem[id=%s, text='%s', completed='%s', todolist='%s']",
                id, text, completed);
    }
}
