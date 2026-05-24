package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.Todo;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
// Todo 응답 DTO
public class TodoResDto {
    private Long id;
    private String title;
    private Boolean status;

    public static TodoResDto from(Todo todo) {
        return TodoResDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .build();
    }
}