package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Subtask;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.dto.SubtaskReqDto;
import com.likelion.likelion_7th.dto.SubtaskResDto;
import com.likelion.likelion_7th.repository.SubtaskRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TodoRepository todoRepository;

    public SubtaskResDto create(SubtaskReqDto requestDto) {
        Todo todo = todoRepository.findById(requestDto.getTodoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다. ID=" + requestDto.getTodoId()));

        Subtask subtask = Subtask.builder()
                .subtaskTitle(requestDto.getSubtaskTitle())
                .subtaskStatus(false)
                .todo(todo)
                .build();

        Subtask savedSubtask = subtaskRepository.save(subtask);
        return SubtaskResDto.from(savedSubtask);
    }
    
    public List<SubtaskResDto> getSubtasksByTodo(Long todoId) {
        return subtaskRepository.findByTodoId(todoId).stream()
                .map(SubtaskResDto::from)
                .collect(Collectors.toList());
    }
}
