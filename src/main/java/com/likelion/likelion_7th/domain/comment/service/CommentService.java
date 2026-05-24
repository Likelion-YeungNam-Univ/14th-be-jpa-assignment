package com.likelion.likelion_7th.domain.comment.service;

import com.likelion.likelion_7th.domain.board.entity.Board;
import com.likelion.likelion_7th.domain.board.repository.BoardRepository;
import com.likelion.likelion_7th.domain.comment.dto.request.CommentCreateRequest;
import com.likelion.likelion_7th.domain.comment.dto.request.CommentUpdateRequest;
import com.likelion.likelion_7th.domain.comment.dto.response.CommentResponse;
import com.likelion.likelion_7th.domain.comment.entity.Comment;
import com.likelion.likelion_7th.domain.comment.repository.CommentRepository;
import com.likelion.likelion_7th.domain.users.entity.User;
import com.likelion.likelion_7th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse createComment(Long boardId, CommentCreateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + request.getUserId()));
        Comment comment = Comment.builder()
                .content(request.getContent())
                .board(board)
                .user(user)
                .build();
        return CommentResponse.from(commentRepository.save(comment));
    }

    public List<CommentResponse> getCommentsByBoard(Long boardId) {
        if (!boardRepository.existsById(boardId)) {
            throw new IllegalArgumentException("Board not found: " + boardId);
        }
        return commentRepository.findByBoardBoardId(boardId).stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
        comment.update(request.getContent());
        return CommentResponse.from(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentId));
        commentRepository.delete(comment);
    }
}
