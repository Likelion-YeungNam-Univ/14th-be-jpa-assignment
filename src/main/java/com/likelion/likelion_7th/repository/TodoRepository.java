package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 투두 레포지토리
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCategoryId(Long categoryId);
    List<Todo> findAllByUserId(Long userId);
}