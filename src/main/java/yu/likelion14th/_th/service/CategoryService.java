package yu.likelion14th._th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th._th.domain.Category;
import yu.likelion14th._th.dto.category.CategoryReqDto;
import yu.likelion14th._th.dto.category.CategoryResDto;
import yu.likelion14th._th.repository.CategoryRepository;

import java.util.List;

// 카테고리 서비스
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 생성
    public CategoryResDto create(CategoryReqDto requestDto) {
        Category category = Category.builder()
                .name(requestDto.getName())
                .iconUrl(requestDto.getIconUrl())
                .build();
        return CategoryResDto.from(categoryRepository.save(category));
    }

    // 전체 카테고리 조회
    public List<CategoryResDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResDto::from)
                .toList();
    }

    // 단일 카테고리 조회
    public CategoryResDto findById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음!"));
        return CategoryResDto.from(category);
    }

    // 카테고리 수정
    public CategoryResDto update(Long categoryId, CategoryReqDto requestDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음!"));
        category.update(requestDto.getName(), requestDto.getIconUrl());
        return CategoryResDto.from(category);
    }

    // 카테고리 삭제
    public void delete(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("해당 카테고리 없음!");
        }
        categoryRepository.deleteById(categoryId);
    }
}
