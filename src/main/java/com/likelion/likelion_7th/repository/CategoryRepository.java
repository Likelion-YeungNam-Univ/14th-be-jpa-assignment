package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByTodoId(Long todoId);
}
