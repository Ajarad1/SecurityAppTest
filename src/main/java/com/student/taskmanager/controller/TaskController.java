package com.student.taskmanager.controller;

import com.student.taskmanager.dto.TaskRequest;
import com.student.taskmanager.dto.TaskResponse;
import com.student.taskmanager.model.User;
import com.student.taskmanager.service.TaskService;
import com.student.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // POST /tasks → create a new task
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@RequestBody TaskRequest request) {
        User currentUser = getAuthenticatedUser();
        return taskService.createTask(request, currentUser);
    }

    // GET /tasks/my → get tasks of the logged-in user
    @GetMapping("/my")
    public List<TaskResponse> getMyTasks() {
        User currentUser = getAuthenticatedUser();
        return taskService.getTasksByUser(currentUser);
    }

    // GET /tasks → list all tasks (ADMIN only)
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // DELETE /tasks/{id} → delete any task (ADMIN only)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    private User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }
}