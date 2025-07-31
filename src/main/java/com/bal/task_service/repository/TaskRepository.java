package com.bal.task_service.repository;

import com.bal.task_service.model.Task;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findAllByUserId(Long userId);

    List<Task> findAllByUserIdAndCompleted(Long userId,boolean completed);

    List<Task> findAllByUserIdAndTitleContainingIgnoreCase(Long userId,String keyword);

    List<Task> findAllByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    Optional<Task> findByIdAndUserId(Long id, Long userId);

}
