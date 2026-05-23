package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResDto {
    private Long id;
    private String categoryName;

    public static CategoryResDto from(Category category) {
        return CategoryResDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }
}
