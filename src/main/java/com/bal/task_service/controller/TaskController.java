package com.bal.task_service.controller;

import com.bal.task_service.client.AuthServiceClient;
import com.bal.task_service.dto.CreateTaskDTO;
import com.bal.task_service.dto.TaskResponseDTO;
import com.bal.task_service.dto.UpdateTaskDTO;
import com.bal.task_service.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;
    private final AuthServiceClient authServiceClient;

    @PostMapping
    public TaskResponseDTO createTask(
            @RequestBody CreateTaskDTO dto,
            @RequestHeader("Authorization") String token) {

        String userId = authServiceClient.getUserIdFromToken(token);
        return taskService.createTask(dto, userId);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllUserTasks(
            @RequestHeader("Authorization") String token) {

        String userId = authServiceClient.getUserIdFromToken(token);
        return taskService.getAllTasksByUser(userId);
    }

    @GetMapping("/{taskId}")
    public TaskResponseDTO getTaskById(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String token) {

        String userId = authServiceClient.getUserIdFromToken(token);
        return taskService.getTaskById(taskId, userId);
    }

    @PutMapping("/{taskId}")
    public TaskResponseDTO updateTask(
            @PathVariable Long taskId,
            @RequestBody UpdateTaskDTO dto,
            @RequestHeader("Authorization") String token) {

        String userId = authServiceClient.getUserIdFromToken(token);
        return taskService.updateTask(taskId, dto, userId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(
            @PathVariable Long taskId,
            @RequestHeader("Authorization") String token) {

        String userId = authServiceClient.getUserIdFromToken(token);
        taskService.deleteTask(taskId, userId);
    }
}