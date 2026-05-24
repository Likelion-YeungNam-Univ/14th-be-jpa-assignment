package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long id); // 게시글 아이디로 찾기
    List<Post> findByTitleAndContent(String title, String content); // 게시글 제목과 내용으로 찾기
}
