package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.CommentReqDto;
import com.likelion.likelion_7th.dto.response.CommentResDto;
import com.likelion.likelion_7th.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Comment 요청 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<CommentResDto> createComment(@RequestBody CommentReqDto req) {
        return ResponseEntity.ok(commentService.createComment(req));
    }

    // 댓글 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<List<CommentResDto>> getComment(@PathVariable Long todoId) {
        return ResponseEntity.ok(commentService.getComment(todoId));
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResDto> updateComment(@PathVariable Long commentId, @RequestBody CommentReqDto req) {
        return ResponseEntity.ok(commentService.updateComment(commentId, req));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}