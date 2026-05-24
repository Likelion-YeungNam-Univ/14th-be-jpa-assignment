package com.likelion.likelion_7th.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {

    @NotBlank(message = "프로젝트 이름은 필수입니다.")
    private String projectName;
}
