package com.example;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_task")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed = false;
}