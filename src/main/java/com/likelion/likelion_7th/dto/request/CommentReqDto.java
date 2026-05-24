package com.likelion.likelion_7th.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 댓글 생성 요청 DTO
public class CommentReqDto {
    private String remark;
    private Long todoId;
}