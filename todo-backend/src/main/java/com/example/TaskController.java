package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks(){
        if (taskRepository.count() == 0){
            throw new RuntimeException("No tasks found in the database");
        }
        return taskRepository.findAll();
    }

    @PostMapping
    public Task creatTask(@RequestBody Task task){
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, Task taskDetails){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task with id " + id + " not found");
        }   
        taskRepository.deleteById(id);
    }
}