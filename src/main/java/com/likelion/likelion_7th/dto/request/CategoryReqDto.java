package com.likelion.likelion_7th.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 카테고리 생성 요청 DTO
public class CategoryReqDto {
    private String name;
    private Long userId;
}