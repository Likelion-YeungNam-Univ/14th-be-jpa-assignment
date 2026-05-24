package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.SubTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 서브투두 레포지토리
public interface SubTodoRepository extends JpaRepository<SubTodo, Long> {
    List<SubTodo> findAllByTodoId(Long todoId);
}