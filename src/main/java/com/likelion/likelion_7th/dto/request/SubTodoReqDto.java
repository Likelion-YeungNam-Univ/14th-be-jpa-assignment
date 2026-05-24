package com.likelion.likelion_7th.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// SubTodo 생성 요청 DTO
public class SubTodoReqDto {
    private String title;
    private Long todoId;
}