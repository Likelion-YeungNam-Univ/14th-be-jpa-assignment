package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.SubTodo;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.dto.request.SubTodoReqDto;
import com.likelion.likelion_7th.dto.response.SubTodoResDto;
import com.likelion.likelion_7th.repository.SubTodoRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTodoService {

    private final SubTodoRepository subTodoRepository;
    private final TodoRepository todoRepository;

    // 서브투두 생성
    public SubTodoResDto createSubTodo(SubTodoReqDto req) {
        Todo todo = todoRepository.findById(req.getTodoId())
                .orElseThrow(() -> new RuntimeException("투두를 찾을 수 없습니다."));
        SubTodo subTodo = SubTodo.builder()
                .title(req.getTitle())
                .status(false)
                .todo(todo)
                .build();
        return SubTodoResDto.from(subTodoRepository.save(subTodo));
    }

    // 서브투두 조회
    public List<SubTodoResDto> getSubTodo(Long todoId) {
        return subTodoRepository.findAllByTodoId(todoId).stream()
                .map(SubTodoResDto::from)
                .collect(Collectors.toList());
    }

    // 서브투두 수정
    public SubTodoResDto updateSubTodo(Long subTodoId, SubTodoReqDto req) {
        SubTodo subTodo = subTodoRepository.findById(subTodoId)
                .orElseThrow(() -> new RuntimeException("서브투두를 찾을 수 없습니다."));
        SubTodo updated = SubTodo.builder()
                .id(subTodo.getId())
                .title(req.getTitle())
                .status(subTodo.getStatus())
                .todo(subTodo.getTodo())
                .build();
        return SubTodoResDto.from(subTodoRepository.save(updated));
    }

    // 서브투두 완료처리
    public SubTodoResDto updateSubTodoStatus(Long subTodoId) {
        SubTodo subTodo = subTodoRepository.findById(subTodoId)
                .orElseThrow(() -> new RuntimeException("서브투두를 찾을 수 없습니다."));
        SubTodo updated = SubTodo.builder()
                .id(subTodo.getId())
                .title(subTodo.getTitle())
                .status(!subTodo.getStatus())
                .todo(subTodo.getTodo())
                .build();
        return SubTodoResDto.from(subTodoRepository.save(updated));
    }

    // 서브투두 삭제
    public void deleteSubTodo(Long subTodoId) {
        subTodoRepository.deleteById(subTodoId);
    }
}