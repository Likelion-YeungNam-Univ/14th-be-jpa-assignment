package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Category;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.TodoReqDto;
import com.likelion.likelion_7th.dto.response.TodoResDto;
import com.likelion.likelion_7th.repository.CategoryRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // 투두 생성
    public TodoResDto createTodo(TodoReqDto req) {
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Todo todo = Todo.builder()
                .title(req.getTitle())
                .status(false)
                .category(category)
                .user(user)
                .build();
        return TodoResDto.from(todoRepository.save(todo));
    }

    // 카테고리 아이디를 통한 목록 조회
    public List<TodoResDto> getTodo(Long categoryId) {
        return todoRepository.findAllByCategoryId(categoryId).stream()
                .map(TodoResDto::from)
                .collect(Collectors.toList());
    }
    // 유저 아이디를 통한 목록 조회
    public List<TodoResDto> getTodos(Long userId) {
        return todoRepository.findAllByUserId(userId).stream()
                .map(TodoResDto::from)
                .collect(Collectors.toList());
    }

    // 투두 완료처리
    public TodoResDto updateStatus(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));
        Todo updated = Todo.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .status(!todo.getStatus())
                .category(todo.getCategory())
                .user(todo.getUser())
                .build();
        return TodoResDto.from(todoRepository.save(updated));
    }

    // 투두 수정
    public TodoResDto updateTodo(Long todoId, TodoReqDto req) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));
        Todo updated = Todo.builder()
                .id(todo.getId())
                .title(req.getTitle())
                .status(todo.getStatus())
                .category(todo.getCategory())
                .user(todo.getUser())
                .build();
        return TodoResDto.from(todoRepository.save(updated));
    }

    // 투두 삭제
    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}