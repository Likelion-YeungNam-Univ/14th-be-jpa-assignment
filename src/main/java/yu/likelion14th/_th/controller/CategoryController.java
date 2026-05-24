package yu.likelion14th._th.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th._th.dto.category.CategoryReqDto;
import yu.likelion14th._th.dto.category.CategoryResDto;
import yu.likelion14th._th.service.CategoryService;

import java.util.List;

// 카테고리 컨트롤러
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping
    public CategoryResDto create(@RequestBody CategoryReqDto requestDto) {
        return categoryService.create(requestDto);
    }

    // 전체 카테고리 조회
    @GetMapping
    public List<CategoryResDto> findAll() {
        return categoryService.findAll();
    }

    // 단일 카테고리 조회
    @GetMapping("/{categoryId}")
    public CategoryResDto findById(@PathVariable Long categoryId) {
        return categoryService.findById(categoryId);
    }

    // 카테고리 수정
    @PutMapping("/{categoryId}")
    public CategoryResDto update(@PathVariable Long categoryId,
                                 @RequestBody CategoryReqDto requestDto) {
        return categoryService.update(categoryId, requestDto);
    }

    // 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);
    }
}
