package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.SubtaskReqDto;
import com.likelion.likelion_7th.dto.SubtaskResDto;
import com.likelion.likelion_7th.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subtaskService;

    @PostMapping
    public ResponseEntity<SubtaskResDto> createSubtask(@RequestBody SubtaskReqDto requestDto) {
        return ResponseEntity.ok(subtaskService.create(requestDto));
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<SubtaskResDto>> getSubtasksByTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(subtaskService.getSubtasksByTodo(todoId));
    }
}

