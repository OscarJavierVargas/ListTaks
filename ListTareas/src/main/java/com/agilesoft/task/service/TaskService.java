package com.agilesoft.task.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agilesoft.task.entity.TaskEntity;
import com.agilesoft.task.entity.UserEntity;
import com.agilesoft.task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }
    		

    
    public ResponseEntity<?> getAllTasksForUser(Long userId) {
    	try {
        return ResponseEntity.ok (taskRepository.findByUserEntity_UserId(userId));  
    	}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage()+": verificar datos ingresados", HttpStatus.OK);
		}
      
    }
    

    public ResponseEntity<?> addTask(TaskEntity task, UserEntity user) {
    	try {
        task.setUserEntity(user);
        taskRepository.save(task);
        return new ResponseEntity<>("tarea creada con exito", HttpStatus.OK);
    	}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage()+": verificar datos ingresados", HttpStatus.OK);
		}
    }

    public ResponseEntity<?> markTaskAsResolved(Long taskId, Long user_Id) {
    	try {
        Optional<TaskEntity> task = taskRepository.findById(taskId);
        if (task.isPresent() && task.get().getUserEntity().getUserId().equals(user_Id)) {
            task.get().setResolved(true);
             Optional.of(taskRepository.save(task.get()));
        }
       // return Optional.empty();
        return new ResponseEntity<>("no hay tarea creada", HttpStatus.OK);
    	}catch(Exception e) {
    		return new ResponseEntity<>(e.getMessage()+": verificar datos ingresados", HttpStatus.OK);
    	}
    }

    public void deleteTask(Long taskId, Long userId) {
        Optional<TaskEntity> task = taskRepository.findById(taskId);
        if (task.isPresent() && task.get().getUserEntity().getUserId().equals(userId)) {
            taskRepository.delete(task.get());
        }
    }
}


