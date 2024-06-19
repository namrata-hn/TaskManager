/**
 * 
 */
package com.springboot.app;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Title is mandatory")
    private String title;
    private String description;
    @Pattern(regexp = "TO DO|IN PROGRESS|DONE", message = "Status must be one of: TO DO, IN PROGRESS, DONE")
    private String status;
    private LocalDate dueDate;
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Priority must be one of: LOW, MEDIUM, HIGH")
    private String priority;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(long id, String title, String description, String status, LocalDate dueDate, String priority){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Task() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
