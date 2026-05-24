package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 카테고리 레포지토리
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUserId(Long userId);
}