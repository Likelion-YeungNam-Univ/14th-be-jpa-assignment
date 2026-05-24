package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.SubTodoReqDto;
import com.likelion.likelion_7th.dto.response.SubTodoResDto;
import com.likelion.likelion_7th.service.SubTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// SubTodo 요청 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/subtodos")
public class SubTodoController {

    private final SubTodoService subTodoService;

    // 서브투두 생성
    @PostMapping
    public ResponseEntity<SubTodoResDto> createSubTodo(@RequestBody SubTodoReqDto req) {
        return ResponseEntity.ok(subTodoService.createSubTodo(req));
    }

    // 서브투두 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<List<SubTodoResDto>> getSubTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(subTodoService.getSubTodo(todoId));
    }

    // 서브투두 수정
    @PatchMapping("/{subTodoId}/title")
    public ResponseEntity<SubTodoResDto> updateSubTodo(@PathVariable Long subTodoId, @RequestBody SubTodoReqDto req) {
        return ResponseEntity.ok(subTodoService.updateSubTodo(subTodoId, req));
    }

    // 서브투두 완료처리
    @PatchMapping("/{subTodoId}/status")
    public ResponseEntity<SubTodoResDto> updateSubTodoStatus(@PathVariable Long subTodoId) {
        return ResponseEntity.ok(subTodoService.updateSubTodoStatus(subTodoId));
    }

    // 서브투두 삭제
    @DeleteMapping("/{subTodoId}")
    public ResponseEntity<Void> deleteSubTodo(@PathVariable Long subTodoId) {
        subTodoService.deleteSubTodo(subTodoId);
        return ResponseEntity.noContent().build();
    }
}