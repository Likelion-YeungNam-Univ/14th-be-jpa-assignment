package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Comment;
import com.likelion.likelion_7th.domain.Like;
import com.likelion.likelion_7th.domain.Post;
import com.likelion.likelion_7th.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post); // 유저와 게시글로 좋아요 찾기
    Optional<Like> findByUserAndComment(User user, Comment comment); // 유저와 댓글로 좋아요 찾기
    int countByPost(Post post); // 게시글 좋아요 수 세기
    int countByComment(Comment comment); // 댓글 좋아요 수 세기
}
