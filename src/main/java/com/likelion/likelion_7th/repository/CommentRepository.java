package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 코멘트 레포지토리
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTodoId(Long todoId);
}