package com.bal.task_service.repository;

import com.bal.task_service.model.Task;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByUserId(String userId);

    List<Task> findAllByUserIdAndCompleted(String userId,boolean completed);

    List<Task> findAllByUserIdAndTitleContainingIgnoreCase(String userOd,String keyword);

    List<Task> findAllByUserIdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);

    Optional<Task> findByIdAndUserId(Long id, String userId);

}
