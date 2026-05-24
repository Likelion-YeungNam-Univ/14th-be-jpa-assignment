package com.likelion.likelion_7th.domain.attachment.service;

import com.likelion.likelion_7th.domain.attachment.dto.request.AttachmentCreateRequest;
import com.likelion.likelion_7th.domain.attachment.dto.response.AttachmentResponse;
import com.likelion.likelion_7th.domain.attachment.entity.Attachment;
import com.likelion.likelion_7th.domain.attachment.repository.AttachmentRepository;
import com.likelion.likelion_7th.domain.board.entity.Board;
import com.likelion.likelion_7th.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public AttachmentResponse createAttachment(Long boardId, AttachmentCreateRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found: " + boardId));
        Attachment attachment = Attachment.builder()
                .originalName(request.getOriginalName())
                .storedName(request.getStoredName())
                .filePath(request.getFilePath())
                .board(board)
                .build();
        return AttachmentResponse.from(attachmentRepository.save(attachment));
    }

    @Transactional
    public void deleteAttachment(Long fileId) {
        Attachment attachment = attachmentRepository.findById(fileId)
                .orElseThrow(() -> new IllegalArgumentException("Attachment not found: " + fileId));
        attachmentRepository.delete(attachment);
    }
}
