package com.likelion.likelion_7th.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    private String postTitle;

    private String postContent;

    @NotNull(message = "ProjectUser ID는 필수입니다.")
    private Long projectUserId;
}
