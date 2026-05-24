package yu.likelion14th._th.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th._th.dto.todo.TodoReqDto;
import yu.likelion14th._th.dto.todo.TodoResDto;
import yu.likelion14th._th.service.TodoService;

import java.util.List;

// 할 일 컨트롤러
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 할 일 생성
    @PostMapping
    public TodoResDto create(@RequestBody TodoReqDto requestDto) {
        return todoService.create(requestDto);
    }

    // 전체 할 일 조회
    @GetMapping
    public List<TodoResDto> findAll() {
        return todoService.findAll();
    }

    // 단일 할 일 조회
    @GetMapping("/{todoId}")
    public TodoResDto findById(@PathVariable Long todoId) {
        return todoService.findById(todoId);
    }

    // 특정 사용자의 할 일 조회
    @GetMapping("/user/{userId}")
    public List<TodoResDto> findAllByUserId(@PathVariable Long userId) {
        return todoService.findAllByUserId(userId);
    }

    // 할 일 수정
    @PutMapping("/{todoId}")
    public TodoResDto update(@PathVariable Long todoId,
                             @RequestBody TodoReqDto requestDto) {
        return todoService.update(todoId, requestDto);
    }

    // 완료 상태 토글
    @PutMapping("/{todoId}/toggle")
    public TodoResDto toggleDone(@PathVariable Long todoId) {
        return todoService.toggleDone(todoId);
    }

    // 할 일 삭제
    @DeleteMapping("/{todoId}")
    public void delete(@PathVariable Long todoId) {
        todoService.delete(todoId);
    }
}
