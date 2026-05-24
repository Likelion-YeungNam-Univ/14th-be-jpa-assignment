package com.likelion.likelion_7th.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Todo 생성 요청 DTO
public class TodoReqDto {
    private String title;
    private Long categoryId;
    private Long userId;
}