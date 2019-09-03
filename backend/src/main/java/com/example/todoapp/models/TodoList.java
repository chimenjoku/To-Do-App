package com.example.todoapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection="todo-lists")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class TodoList{
    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String title;
    private Date createdAt = new Date();

    public TodoList() {
        super();
    }

    public TodoList(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title= title;
    }


    public void setId(String id) {
        this.id = id;
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
                "TodoList[id=%s, title='%s']",
                id, title);
    }
}
