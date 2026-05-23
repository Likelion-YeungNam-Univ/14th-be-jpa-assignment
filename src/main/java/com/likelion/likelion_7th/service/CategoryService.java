package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Category;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.dto.CategoryReqDto;
import com.likelion.likelion_7th.dto.CategoryResDto;
import com.likelion.likelion_7th.repository.CategoryRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;

    public CategoryResDto create(CategoryReqDto requestDto) {
        Todo todo = todoRepository.findById(requestDto.getTodoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다. ID=" + requestDto.getTodoId()));

        Category category = Category.builder()
                .categoryName(requestDto.getCategoryName())
                .todo(todo)
                .build();

        Category savedCategory = categoryRepository.save(category);
        return CategoryResDto.from(savedCategory);
    }
    
    public List<CategoryResDto> getCategoriesByTodo(Long todoId) {
        return categoryRepository.findByTodoId(todoId).stream()
                .map(CategoryResDto::from)
                .collect(Collectors.toList());
    }
}
