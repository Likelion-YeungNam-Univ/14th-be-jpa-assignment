package com.likelion.likelion_7th.dto.Res;

import com.likelion.likelion_7th.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResDto {
    private Long id;
    private String commentBody;
    private String username;
    private int likesCount;

    @Builder
    public CommentResDto(Long id, String commentBody, String username, int likesCount) {
        this.id = id;
        this.commentBody = commentBody;
        this.username = username;
        this.likesCount = likesCount;
    }

    public static CommentResDto from(Comment comment) { // 댓글을 댓글 디티오로 변환
        return CommentResDto.builder()
                .id(comment.getId())
                .commentBody(comment.getComment_body())
                .username(comment.getUser() != null ? comment.getUser().getUsername() : null) // 댓글 작성자
                .likesCount(comment.getLike() != null ? comment.getLike().size() : 0) // 댓글 좋아요 수
                .build();
    }
}
