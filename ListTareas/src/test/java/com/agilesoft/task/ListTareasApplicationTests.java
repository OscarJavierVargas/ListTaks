package com.agilesoft.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import com.agilesoft.task.entity.TaskEntity;
import com.agilesoft.task.repository.TaskRepository;
import com.agilesoft.task.service.TaskService;
import com.agilesoft.task.service.UserService;

@SpringBootTest
class ListTareasApplicationTests {

	@Test
	void contextLoads() {
	}
	
	  @Mock
	    private TaskRepository taskRepository;

	    @Mock
	    private UserService userService;

	    @InjectMocks
	    private TaskService taskService;

	    @BeforeEach
	    void setUp() {
	       // MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetAllTasksForUser() {
	        // Arrange
	        Long userId = 1L;
	        TaskEntity task1 = new TaskEntity();
	        TaskEntity task2 = new TaskEntity();
	        List<TaskEntity> tasks = Arrays.asList(task1, task2);
	        when(taskRepository.findByUserEntity_UserId(userId)).thenReturn(tasks);

	        // Act
	        ResponseEntity<?> response = taskService.getAllTasksForUser(userId);

	        // Assert
	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(tasks, response.getBody());
	    }

	    @Test
	    void testGetAllTasksForUser_Exception() {
	        // Arrange
	        Long userId = 1L;
	        when(taskRepository.findByUserEntity_UserId(userId)).thenThrow(new RuntimeException("Error"));

	        // Act
	        ResponseEntity<?> response = taskService.getAllTasksForUser(userId);

	        // Assert
	        assertEquals(200, response.getStatusCodeValue());
	        assertTrue(response.getBody().toString().contains("verificar datos ingresados"));
	    }

}
