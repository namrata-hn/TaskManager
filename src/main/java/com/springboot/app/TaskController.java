package com.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping("/tasks/users/{user_id}")
    public Task createTask(@RequestBody Task task, @PathVariable long user_id){
        return service.saveTaskToUser(task, user_id);
    }

    @GetMapping("/tasks/users/{user_id}/{task_id}")
    public Optional<Task> getTaskById(@PathVariable long user_id, @PathVariable long task_id){
        return service.getTaskById(user_id, task_id);
    }

    @GetMapping("/tasks/users")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/tasks/users/{user_id}")
    public List<Task> getTasksByUser(@PathVariable long user_id){
        return service.findTasksByUserId(user_id);
    }

    @PutMapping("/tasks/users/{user_id}/{task_id}")
    public Task updateTask(@PathVariable long user_id, @PathVariable long task_id, @RequestBody Task task){
        return service.updateTask(user_id, task_id, task);
    }

    @DeleteMapping("/tasks/users/{user_id}/{task_id}")
    public void deleteTask(@PathVariable long user_id, @PathVariable long task_id){
        service.deleteTask(user_id, task_id);
    }
}
