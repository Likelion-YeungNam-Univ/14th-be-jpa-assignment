package com.likelion.likelion_7th.domain.comment.controller;

import com.likelion.likelion_7th.domain.comment.dto.request.CommentCreateRequest;
import com.likelion.likelion_7th.domain.comment.dto.request.CommentUpdateRequest;
import com.likelion.likelion_7th.domain.comment.dto.response.CommentResponse;
import com.likelion.likelion_7th.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long boardId,
            @Valid @RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(boardId, request));
    }

    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentsByBoard(boardId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentUpdateRequest request) {
        return ResponseEntity.ok(commentService.updateComment(commentId, request));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
