package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Category;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.CategoryReqDto;
import com.likelion.likelion_7th.dto.response.CategoryResDto;
import com.likelion.likelion_7th.repository.CategoryRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    // 카테고리 생성
    public CategoryResDto createCategory(CategoryReqDto req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Category category = Category.builder()
                .name(req.getName())
                .user(user)
                .build();
        return CategoryResDto.from(categoryRepository.save(category));
    }

    // 카테고리 목록 조회
    public List<CategoryResDto> getCategory(Long userId) {
        return categoryRepository.findAllByUserId(userId).stream()
                .map(CategoryResDto::from)
                .collect(Collectors.toList());
    }

    // 카테고리 수정
    public CategoryResDto updateCategory(Long categoryId, CategoryReqDto req) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
        Category updated = Category.builder()
                .id(category.getId())
                .name(req.getName())
                .user(category.getUser())
                .build();
        return CategoryResDto.from(categoryRepository.save(updated));
    }

    // 카테고리 삭제
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}