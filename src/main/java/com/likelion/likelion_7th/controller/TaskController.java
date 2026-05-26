package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.TaskRequest;
import com.likelion.likelion_7th.dto.request.TaskUpdateRequest;
import com.likelion.likelion_7th.dto.response.TaskResponse;
import com.likelion.likelion_7th.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTaskList() {
        return ResponseEntity.ok(taskService.getTaskList());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, @RequestBody TaskUpdateRequest request) {
        return ResponseEntity.ok(taskService.updateTask(taskId, request));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
