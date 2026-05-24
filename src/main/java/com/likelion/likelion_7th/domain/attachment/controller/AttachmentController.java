package com.likelion.likelion_7th.domain.attachment.controller;

import com.likelion.likelion_7th.domain.attachment.dto.request.AttachmentCreateRequest;
import com.likelion.likelion_7th.domain.attachment.dto.response.AttachmentResponse;
import com.likelion.likelion_7th.domain.attachment.service.AttachmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/boards/{boardId}/attachments")
    public ResponseEntity<AttachmentResponse> createAttachment(
            @PathVariable Long boardId,
            @Valid @RequestBody AttachmentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentService.createAttachment(boardId, request));
    }

    @DeleteMapping("/attachments/{fileId}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long fileId) {
        attachmentService.deleteAttachment(fileId);
        return ResponseEntity.noContent().build();
    }
}
