package com.likelion.likelion_7th.domain.board.dto.response;

import com.likelion.likelion_7th.domain.attachment.dto.response.AttachmentResponse;
import com.likelion.likelion_7th.domain.board.entity.Board;
import com.likelion.likelion_7th.domain.comment.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class BoardDetailResponse {

    private Long boardId;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;
    private List<AttachmentResponse> attachments;

    public static BoardDetailResponse from(Board board) {
        return BoardDetailResponse.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .userId(board.getUser().getUserId())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .comments(board.getComments().stream()
                        .map(CommentResponse::from)
                        .collect(Collectors.toList()))
                .attachments(board.getAttachments().stream()
                        .map(AttachmentResponse::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
