package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.Req.CommentReqDto;
import com.likelion.likelion_7th.dto.Res.CommentResDto;
import com.likelion.likelion_7th.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments") // 댓글 작성
    public CommentResDto createComment(@PathVariable Long postId, @RequestBody CommentReqDto commentReqDto) { // postId: 게시글 번호, commentReqDto: 댓글 정보
        return commentService.createComment(postId, commentReqDto); 
    }

    @PutMapping("/comments/{id}") // 댓글 수정
    public CommentResDto updateComment(@PathVariable Long id, @RequestBody CommentReqDto commentReqDto) { // id: 댓글 번호, commentReqDto: 댓글 정보
        return commentService.updateComment(id, commentReqDto);
    }

    @DeleteMapping("/comments/{id}") // 댓글 삭제
    public void deleteComment(@PathVariable Long id) { // id: 댓글 번호
        commentService.deleteComment(id);
    }
}
