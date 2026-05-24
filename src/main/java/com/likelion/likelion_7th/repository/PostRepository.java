package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
