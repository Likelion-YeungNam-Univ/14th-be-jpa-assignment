package yu.likelion14th._th.dto.category;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th._th.domain.Category;

// 카테고리 응답 DTO
@Getter
public class CategoryResDto {

    private Long categoryId;
    private String name;
    private String iconUrl;

    @Builder
    public CategoryResDto(Long categoryId, String name, String iconUrl) {
        this.categoryId = categoryId;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public static CategoryResDto from(Category category) {
        return CategoryResDto.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .iconUrl(category.getIconUrl())
                .build();
    }
}
