package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.TodoReqDto;
import com.likelion.likelion_7th.dto.response.TodoResDto;
import com.likelion.likelion_7th.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Todo 요청 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    // 투두 생성
    @PostMapping
    public ResponseEntity<TodoResDto> createTodo(@RequestBody TodoReqDto req) {
        return ResponseEntity.ok(todoService.createTodo(req));
    }

    // 투두 목록 조회(카테고리 아이디)
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<TodoResDto>> getTodo(@PathVariable Long categoryId) {
        return ResponseEntity.ok(todoService.getTodo(categoryId));
    }
    // 투두 목록 조회(유저 아이디)
    @GetMapping("/{userId}")
    public ResponseEntity<List<TodoResDto>> getTodos(@PathVariable Long userId) {
        return ResponseEntity.ok(todoService.getTodos(userId));
    }

    // 투두 완료처리
    @PatchMapping("/{todoId}/status")
    public ResponseEntity<TodoResDto> updateStatus(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.updateStatus(todoId));
    }

    // 투두 수정
    @PatchMapping("/{todoId}/title")
    public ResponseEntity<TodoResDto> updateTodo(@PathVariable Long todoId, @RequestBody TodoReqDto req) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, req));
    }

    // 투두 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
            return ResponseEntity.noContent().build();
    }
}