package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.TodoReqDto;
import com.likelion.likelion_7th.dto.TodoResDto;
import com.likelion.likelion_7th.service.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResDto> createTodo(@RequestBody TodoReqDto requestDto) {
        return ResponseEntity.ok(todoService.create(requestDto));
    }

    @GetMapping("/day/{dayId}")
    public ResponseEntity<List<TodoResDto>> getTodoByDay(@PathVariable Long dayId) {
        return ResponseEntity.ok(todoService.getTodosByDay(dayId));
    }

    @PatchMapping("/{todoId}/status")
    public ResponseEntity<TodoResDto> toggleStatus(@PathVariable Long todoId) {
        return ResponseEntity.ok((todoService.toggleStatus(todoId)));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) { // 👈 여기를 String으로 변경
        todoService.delete(todoId);
        return ResponseEntity.ok("할 일이 성공적으로 삭제되었습니다");
    }
}
