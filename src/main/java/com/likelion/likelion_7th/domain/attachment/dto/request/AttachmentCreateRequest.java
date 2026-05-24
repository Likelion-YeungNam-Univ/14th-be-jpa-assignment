package com.likelion.likelion_7th.domain.attachment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachmentCreateRequest {

    @NotBlank(message = "원본 파일명은 필수입니다.")
    private String originalName;

    @NotBlank(message = "저장 파일명은 필수입니다.")
    private String storedName;

    @NotBlank(message = "파일 경로는 필수입니다.")
    private String filePath;
}
