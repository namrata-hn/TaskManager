package com.springboot.app;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceTests {

    @InjectMocks
    private TaskService testService;
    @Mock
    private TaskRepository testRepository;
    private Task task1;


    @BeforeEach
    public void setUp(){
        task1 = new Task(1L, "Task 1", "description for task 1", "IN PROGRESS",
                LocalDate.now(), "MEDIUM");
    }

    @Test
    public void testCreateTask(){
        when(testRepository.save(task1)).thenReturn(task1);
        Task createdTask = testService.createTask(task1);
        assertEquals(task1, createdTask);
        verify(testRepository, times(1)).save(task1);
    }

    @Test
    public void testGetTaskById(){
        when(testRepository.findById(1L)).thenReturn(Optional.of(task1));
        Optional<Task> returnedTask = testService.getTaskById(1L, 1L);
        assertTrue(returnedTask.isPresent());
        assertEquals(Optional.of(task1), returnedTask);
        verify(testRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllTasks(){
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        when(testRepository.findAll()).thenReturn(taskList);
        List<Task> returnedList = testService.getAllTasks();
        assertEquals(taskList, returnedList);
        assertEquals(taskList.size(), returnedList.size());
        verify(testRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateTasks(){
        Task updatedTask = new Task(1L, "Task 1", "description for task 1", "DONE",
                LocalDate.now().plusDays(1), "LOW");
        when(testRepository.findById(1L)).thenReturn(Optional.empty());
        Task result = testService.updateTask(1L,1L, updatedTask);
        assertNull(result);
        verify(testRepository, times(1)).findById(1L);
        verify(testRepository, never()).save(any(Task.class));
    }

    @Test
    public void testDeleteTask(){
        testService.deleteTask(1L, 1L);
        verify(testRepository, times(1)).deleteById(1L);
    }
}
