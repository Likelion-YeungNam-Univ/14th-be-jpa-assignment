package com.likelion.likelion_7th.dto.Req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReqDto {
    private Long userId;
    private String commentBody;
}
