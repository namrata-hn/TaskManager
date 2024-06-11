package com.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return service.createTask(task);
    }

    @GetMapping("/tasks/{id}")
    public Optional<Task> getTaskById(@PathVariable long id){
        return service.getTaskById(id);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody Task task){
        return service.updateTask(id, task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable long id){
        service.deleteTask(id);
    }
}
