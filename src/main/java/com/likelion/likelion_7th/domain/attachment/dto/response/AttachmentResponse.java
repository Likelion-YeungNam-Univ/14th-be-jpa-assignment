package com.likelion.likelion_7th.domain.attachment.dto.response;

import com.likelion.likelion_7th.domain.attachment.entity.Attachment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AttachmentResponse {

    private Long fileId;
    private String originalName;
    private String storedName;
    private String filePath;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AttachmentResponse from(Attachment attachment) {
        return AttachmentResponse.builder()
                .fileId(attachment.getFileId())
                .originalName(attachment.getOriginalName())
                .storedName(attachment.getStoredName())
                .filePath(attachment.getFilePath())
                .boardId(attachment.getBoard().getBoardId())
                .createdAt(attachment.getCreatedAt())
                .updatedAt(attachment.getUpdatedAt())
                .build();
    }
}
