package com.bal.task_service.service;

import com.bal.task_service.dto.CreateTaskDTO;
import com.bal.task_service.dto.TaskResponseDTO;
import com.bal.task_service.dto.UpdateTaskDTO;
import com.bal.task_service.model.Task;

import java.util.List;

public interface ITaskService {
    TaskResponseDTO createTask(CreateTaskDTO dto, String userId);
    List<TaskResponseDTO> getAllTasksByUser(String userId);
    TaskResponseDTO getTaskById(Long id, String userId);
    TaskResponseDTO updateTask(Long id, UpdateTaskDTO dto, String userId);
    void deleteTask(Long id, String userId);
}
