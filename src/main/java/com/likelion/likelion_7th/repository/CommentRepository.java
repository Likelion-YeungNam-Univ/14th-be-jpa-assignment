package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // 게시글 아이디로 댓글 찾기
}
