package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) {
        if (taskRepository.count() == 0) {
            Task task1 = new Task();
            task1.setTitle("Buy groceries");
            task1.setCompleted(false);

            Task task2 = new Task();
            task2.setTitle("Finish homework");
            task2.setCompleted(false);

            Task task3 = new Task();
            task3.setTitle("Call mom");
            task3.setCompleted(true);

            taskRepository.saveAll(java.util.List.of(task1, task2, task3));
            System.out.println("3条初始化数据插入成功");
        } else {
            System.out.println("数据库中已有任务数据，跳过初始化插入");
        }
    }
}
