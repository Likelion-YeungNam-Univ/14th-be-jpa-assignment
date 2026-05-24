package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Comment;
import com.likelion.likelion_7th.domain.Todo;
import com.likelion.likelion_7th.dto.request.CommentReqDto;
import com.likelion.likelion_7th.dto.response.CommentResDto;
import com.likelion.likelion_7th.repository.CommentRepository;
import com.likelion.likelion_7th.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    // 댓글 생성
    public CommentResDto createComment(CommentReqDto req) {
        Todo todo = todoRepository.findById(req.getTodoId())
                .orElseThrow(() -> new RuntimeException("투두를 찾을 수 없습니다."));
        Comment comment = Comment.builder()
                .remark(req.getRemark())
                .todo(todo)
                .build();
        return CommentResDto.from(commentRepository.save(comment));
    }

    // 댓글 조회
    public List<CommentResDto> getComment(Long todoId) {
        return commentRepository.findAllByTodoId(todoId).stream()
                .map(CommentResDto::from)
                .collect(Collectors.toList());
    }

    // 댓글 수정
    public CommentResDto updateComment(Long commentId, CommentReqDto req) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        Comment updated = Comment.builder()
                .id(comment.getId())
                .remark(req.getRemark())
                .todo(comment.getTodo())
                .build();
        return CommentResDto.from(commentRepository.save(updated));
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}