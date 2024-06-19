package com.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    private UserRepository userRepository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Task task){
        return repository.save(task);
    }

    public Task saveTaskToUser(Task task, Long userid){
        User user = userRepository.findById(userid).get();
        task.setUser(user);
        return repository.save(task);
    }
    
    public Optional<Task> getTaskById(Long userid, Long id){
        User user = userRepository.findById(userid).get();
        return repository.findById(id);
    }
    
    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task updateTask(Long user_id, Long task_id, Task task){
        User user = userRepository.findById(user_id).get();
        Optional<Task> optionalTask = repository.findById(task_id);
        if (optionalTask.isEmpty()) {
            return null;
        } else {
            Task toUpdate = optionalTask.get();
            toUpdate.setUser(user);
            toUpdate.setTitle(task.getTitle());
            toUpdate.setDescription(task.getDescription());
            toUpdate.setStatus(task.getStatus());
            toUpdate.setDueDate(task.getDueDate());
            toUpdate.setPriority(task.getPriority());
            return repository.save(toUpdate);
        }
    }

    public void deleteTask(Long user_id, Long task_id){
        repository.deleteById(task_id);
    }

    public List<Task> findTasksByUserId(Long user_id){
        return repository.findTasksByUserId(user_id);
    }
}
