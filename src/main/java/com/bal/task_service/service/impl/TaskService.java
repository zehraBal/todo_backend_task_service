package com.bal.task_service.service.impl;

import com.bal.task_service.dto.CreateTaskDTO;
import com.bal.task_service.dto.TaskResponseDTO;
import com.bal.task_service.dto.UpdateTaskDTO;
import com.bal.task_service.model.Task;
import com.bal.task_service.repository.TaskRepository;
import com.bal.task_service.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private  final TaskRepository taskRepository;


    @Override
    public TaskResponseDTO createTask(CreateTaskDTO dto, Long userId) {
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(false)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToResponseDTO(savedTask);
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByUser(Long userId) {
        return taskRepository.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO getTaskById(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Task not found or unauthorized access."));
        return mapToResponseDTO(task);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, UpdateTaskDTO dto, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Task not found or unauthorized access."));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());

        Task updatedTask = taskRepository.save(task);
        return mapToResponseDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Task not found or unauthorized access."));
        taskRepository.delete(task);
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .createdAt(task.getCreatedAt())
                .build();
    }
}


