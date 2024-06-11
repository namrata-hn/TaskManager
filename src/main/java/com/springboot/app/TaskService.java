package com.springboot.app;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task){
        return repository.save(task);
    }
    
    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);
    }
    
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task updateTask(Long id, Task task){
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isEmpty()) {
            return null;
        } else {
            Task toUpdate = optionalTask.get();
            toUpdate.setTitle(task.getTitle());
            toUpdate.setDescription(task.getDescription());
            toUpdate.setStatus(task.getStatus());
            toUpdate.setDueDate(task.getDueDate());
            toUpdate.setPriority(task.getPriority());
            return repository.save(toUpdate);
        }
    }

    public void deleteTask(Long id){
        repository.deleteById(id);
    }
}
