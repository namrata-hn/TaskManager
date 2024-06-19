package com.springboot.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerTests {

    @Autowired
    private TaskService testService;
    @Autowired
    private TaskRepository testRepository;
    @Autowired
    private MockMvc mockMvc;
    private Task task1;

    @BeforeEach
    void beforeEach(){
        task1 = new Task(1L, "Task 1", "description for task 1", "IN PROGRESS",
                LocalDate.now(), "MEDIUM");
        testRepository.save(task1);
    }

    @Test
    public void testCreateTask() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Task 1\",\"description\":\"description for task 1\",\"status\":\"IN PROGRESS\",\"dueDate\":\"" + LocalDate.now() + "\",\"priority\":\"MEDIUM\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("description for task 1"))
                .andExpect(jsonPath("$.status").value("IN PROGRESS"))
                .andExpect(jsonPath("$.priority").value("MEDIUM"));
    }

    @Test
    public void testGetTaskById() throws Exception {
        mockMvc.perform(get("/tasks/23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"23\",\"title\":\"Task 1\",\"description\":\"description for task 1\",\"status\":\"IN PROGRESS\",\"dueDate\":\"" + LocalDate.now() + "\",\"priority\":\"MEDIUM\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("23"))
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("description for task 1"))
                .andExpect(jsonPath("$.status").value("IN PROGRESS"))
                .andExpect(jsonPath("$.priority").value("MEDIUM"));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[" +
                                "    {" +
                                "        \"id\": 1," +
                                "        \"title\": \"Finish report\"," +
                                "        \"description\": \"Write final report for Karsun project\"," +
                                "        \"status\": \"TO DO\"," +
                                "        \"dueDate\": \"2024-07-01\"," +
                                "        \"priority\": \"HIGH\"" +
                                "    }," +
                                "    {" +
                                "        \"id\": 2," +
                                "        \"title\": \"Prepare presentation\"," +
                                "        \"description\": \"Create slides for monthly meeting\"," +
                                "        \"status\": \"IN PROGRESS\"," +
                                "        \"dueDate\": \"2024-07-15\"," +
                                "        \"priority\": \"MEDIUM\"" +
                                "    }," +
                                "    {" +
                                "        \"id\": 3," +
                                "        \"title\": \"Follow up on emails\"," +
                                "        \"description\": \"Respond to emails\"," +
                                "        \"status\": \"DONE\"," +
                                "        \"dueDate\": \"2024-07-20\"," +
                                "        \"priority\": \"LOW\"" +
                                "    }," +
                                "    {" +
                                "        \"id\": 5," +
                                "        \"title\": \"Task 1\"," +
                                "        \"description\": \"description for task 1\"," +
                                "        \"status\": \"IN PROGRESS\"," +
                                "        \"dueDate\": \"2024-06-10\"," +
                                "        \"priority\": \"MEDIUM\"" +
                                "    }," +
                                "    {" +
                                "        \"id\": 6," +
                                "        \"title\": \"Task 1\"," +
                                "        \"description\": \"description for task 1\"," +
                                "        \"status\": \"IN PROGRESS\"," +
                                "        \"dueDate\": \"2024-06-10\"," +
                                "        \"priority\": \"MEDIUM\"" +
                                "    }," +
                                "    {" +
                                "        \"id\": 7," +
                                "        \"title\": \"Task 1\"," +
                                "        \"description\": \"description for task 1\"," +
                                "        \"status\": \"IN PROGRESS\"," +
                                "        \"dueDate\": \"2024-06-10\"," +
                                "        \"priority\": \"MEDIUM\"" +
                                "    }" +
                                "]"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        //need id
        mockMvc.perform(put("/tasks/23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"23\", \"title\":\"Task 1\",\"description\":\"description for task 1\",\"status\":\"IN PROGRESS\",\"dueDate\":\"" + LocalDate.now() + "\",\"priority\":\"MEDIUM\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("23"))
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("description for task 1"))
                .andExpect(jsonPath("$.status").value("IN PROGRESS"))
                .andExpect(jsonPath("$.priority").value("MEDIUM"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //delete is a void method; show in postman
    }
}
