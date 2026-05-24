package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.Category;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
// 카테고리 응답 DTO
public class CategoryResDto {
    private Long id;
    private String name;

    public static CategoryResDto from(Category category) {
        return CategoryResDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}