package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Day;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.dto.TodoReqDto;
import com.likelion.likelion_7th.dto.TodoResDto;
import com.likelion.likelion_7th.repository.DayRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final DayRepository dayRepository;

    public TodoResDto create(TodoReqDto requestDto) {
        Day day = dayRepository.findById(requestDto.getDayId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다. ID=" + requestDto.getDayId()));

        Todo todo = Todo.builder()
                .todoTitle(requestDto.getTodoTitle())
                .todoContent(requestDto.getTodoContent())
                .todoStatus(false)
                .day(day)
                .build();

        Todo savedTodo = todoRepository.save(todo);
        return TodoResDto.from(savedTodo);
    }

    public List<TodoResDto> getTodosByDay(Long dayId) {
        return todoRepository.findByDayId(dayId).stream()
                .map(TodoResDto::from)
                .collect(Collectors.toList());
    }

    public TodoResDto toggleStatus(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다. ID=" + todoId));

        todo.updateStatus(!todo.getTodoStatus());
        return TodoResDto.from(todo);
    }
    
    public void delete(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다. ID=" + todoId));
        todoRepository.delete(todo);
    }
}