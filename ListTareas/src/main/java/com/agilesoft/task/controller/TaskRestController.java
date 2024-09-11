package com.agilesoft.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.agilesoft.task.entity.TaskEntity;
import com.agilesoft.task.entity.UserEntity;
import com.agilesoft.task.service.TaskService;
import com.agilesoft.task.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskRestController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getTasks(Authentication authentication) {
        UserEntity userr = userService.findByUsername(authentication.getName());
        return taskService.getAllTasksForUser(userr.getUserId());
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskEntity task, Authentication authentication) {
        UserEntity userr = userService.findByUsername(authentication.getName());
        return (taskService.addTask(task, userr));
    }

    @PutMapping("/{taskId}/resolve")
    public ResponseEntity<?> markAsResolved(@PathVariable Long taskId, Authentication authentication) {
    	UserEntity userr  = userService.findByUsername(authentication.getName());
    	return taskService.markTaskAsResolved(taskId, userr.getUserId());
//        return ((Object) taskService.markTaskAsResolved(taskId, userr.getUserId()))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, Authentication authentication) {
    	UserEntity userr  = userService.findByUsername(authentication.getName());
        taskService.deleteTask(taskId, userr.getUserId());
        return ResponseEntity.noContent().build();
    }
}
