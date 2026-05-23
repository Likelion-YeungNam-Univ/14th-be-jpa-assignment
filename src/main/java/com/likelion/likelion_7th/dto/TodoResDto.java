package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Todo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoResDto {
    private Long id;
    private String todoTitle;
    private String todoContent;
    private Boolean todoStatus;

    public static TodoResDto from(Todo todo) {
        return TodoResDto.builder()
                .id(todo.getId())
                .todoTitle(todo.getTodoTitle())
                .todoContent(todo.getTodoContent())
                .todoStatus(todo.getTodoStatus())
                .build();
    }
}
