package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.CategoryReqDto;
import com.likelion.likelion_7th.dto.response.CategoryResDto;
import com.likelion.likelion_7th.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Category 요청 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping
    public ResponseEntity<CategoryResDto> createCategory(@RequestBody CategoryReqDto req) {
        return ResponseEntity.ok(categoryService.createCategory(req));
    }

    // 카테고리 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CategoryResDto>> getCategory(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.getCategory(userId));
    }

    // 카테고리 수정
    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryReqDto req) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, req));
    }

    // 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}