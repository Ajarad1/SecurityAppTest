package com.student.taskmanager.service;

import com.student.taskmanager.dto.TaskRequest;
import com.student.taskmanager.dto.TaskResponse;
import com.student.taskmanager.model.Task;
import com.student.taskmanager.model.User;
import com.student.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_ShouldReturnTaskResponse() {
        User mockUser = new User();
        mockUser.setUsername("john");

        TaskRequest request = new TaskRequest("Test Title", "Desc");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle(request.title());
        savedTask.setDescription(request.description());
        savedTask.setOwner(mockUser);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponse response = taskService.createTask(request, mockUser);

        assertNotNull(response);
        assertEquals("Test Title", response.title());
        assertEquals("john", response.ownerUsername());
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void getTasksByUser_ShouldReturnList() {
        User mockUser = new User();
        mockUser.setUsername("john");

        Task task1 = new Task();
        task1.setId(1L);
        task1.setOwner(mockUser);

        when(taskRepository.findAllByOwner(mockUser)).thenReturn(List.of(task1));

        List<TaskResponse> responses = taskService.getTasksByUser(mockUser);

        assertEquals(1, responses.size());
        verify(taskRepository, times(1)).findAllByOwner(mockUser);
    }
}