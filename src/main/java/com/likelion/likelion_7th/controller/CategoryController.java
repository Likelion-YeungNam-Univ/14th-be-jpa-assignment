package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.CategoryReqDto;
import com.likelion.likelion_7th.dto.CategoryResDto;
import com.likelion.likelion_7th.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResDto> createCategory(@RequestBody CategoryReqDto requestDto) {
        return ResponseEntity.ok(categoryService.create(requestDto));
    }

    @GetMapping("/todo/{todoId}")
    public ResponseEntity<List<CategoryResDto>> getCategoriesByTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(categoryService.getCategoriesByTodo(todoId));
    }
}
