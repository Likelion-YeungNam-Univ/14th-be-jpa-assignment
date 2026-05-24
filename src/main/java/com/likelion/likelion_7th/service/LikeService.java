package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Comment;
import com.likelion.likelion_7th.domain.Like;
import com.likelion.likelion_7th.domain.Post;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.repository.CommentRepository;
import com.likelion.likelion_7th.repository.LikeRepository;
import com.likelion.likelion_7th.repository.PostRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    // 게시글 좋아요
    @Transactional
    public boolean togglePostLike(Long postId, Long userId) { // 게시글 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
                
        User user = userRepository.findById(userId) // 유저 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);
        if (existingLike.isPresent()) { // 좋아요가 이미 존재하는 경우
            // 이미 좋아요가 존재하는 경우 취소 (삭제)
            likeRepository.delete(existingLike.get()); // 좋아요 삭제
            post.getLike().remove(existingLike.get()); // 게시글에서 좋아요 삭제
            user.getLike().remove(existingLike.get()); // 유저에서 좋아요 삭제
            return false;
        } else { // 좋아요가 존재하지 않는 경우
            // 좋아요 생성
            Like like = Like.builder()
                    .like_count(1)
                    .build();
            like.confirmUser(user); // 좋아요와 사용자 연결
            like.confirmPost(post); // 좋아요와 게시글 연결
            likeRepository.save(like);
            return true; // 좋아요 성공
        }
    }

    @Transactional  // 댓글 좋아요
    public boolean toggleCommentLike(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId) // 댓글 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음!"));
        User user = userRepository.findById(userId) // 유저 찾기
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Optional<Like> existingLike = likeRepository.findByUserAndComment(user, comment); // 좋아요 찾기
        if (existingLike.isPresent()) { // 좋아요가 이미 존재하는 경우
            likeRepository.delete(existingLike.get()); // 좋아요 삭제
            comment.getLike().remove(existingLike.get()); // 댓글에서 좋아요 삭제
            user.getLike().remove(existingLike.get()); // 유저에서 좋아요 삭제
            return false;
        } else { // 좋아요가 존재하지 않는 경우
            Like like = Like.builder() // 좋아요 생성
                    .like_count(1)
                    .build();
            like.confirmUser(user); // 좋아요와 사용자 연결
            like.confirmComment(comment); // 좋아요와 댓글 연결
            likeRepository.save(like); // 좋아요 저장
            return true; // 좋아요 성공
        }
    }
}
