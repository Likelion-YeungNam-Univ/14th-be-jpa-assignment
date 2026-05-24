package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/posts/{postId}/likes") // 게시글 좋아요
    public String togglePostLike(@PathVariable Long postId, @RequestParam Long userId) { // postId: 게시글 번호, userId: 유저 번호
        boolean liked = likeService.togglePostLike(postId, userId);
        return liked ? "좋아요 성공" : "좋아요 취소";
    }

    @PostMapping("/comments/{commentId}/likes") // 댓글 좋아요
    public String toggleCommentLike(@PathVariable Long commentId, @RequestParam Long userId) { // commentId: 댓글 번호, userId: 유저 번호
        boolean liked = likeService.toggleCommentLike(commentId, userId);
        return liked ? "좋아요 성공" : "좋아요 취소";
    }
}
