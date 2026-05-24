package com.likelion.likelion_7th.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUpdateRequest {

    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
