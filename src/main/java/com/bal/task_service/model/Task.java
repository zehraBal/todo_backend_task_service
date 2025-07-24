package com.bal.task_service.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private boolean completed;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="user_id",nullable = false)
    private String userId;

    /* @PrePersist, JPA (Java Persistence API)'de bir entity nesnesi veritabanına kaydedilmeden hemen önce
     * çalıştırılan bir "lifecycle callback" anotasyonudur.
     * Yani Spring/JPA, bir Task nesnesini save() ile kaydedeceği zaman bu metodu otomatik olarak çağırır.
     * */
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
