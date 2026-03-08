package com.example;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@EntityListeners(AuditingEntityListener.class)

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private boolean completed;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
