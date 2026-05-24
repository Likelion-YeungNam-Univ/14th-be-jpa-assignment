package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.SubTodo;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
// SubTodo 응답 DTO
public class SubTodoResDto {
    private Long id;
    private String title;
    private Boolean status;

    public static SubTodoResDto from(SubTodo subTodo) {
        return SubTodoResDto.builder()
                .id(subTodo.getId())
                .title(subTodo.getTitle())
                .status(subTodo.getStatus())
                .build();
    }
}