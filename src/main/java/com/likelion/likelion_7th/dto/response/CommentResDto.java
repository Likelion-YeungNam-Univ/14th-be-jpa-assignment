package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.Comment;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
// 댓글 응답 DTO
public class CommentResDto {
    private Long id;
    private String remark;

    public static CommentResDto from(Comment comment) {
        return CommentResDto.builder()
                .id(comment.getId())
                .remark(comment.getRemark())
                .build();
    }
}