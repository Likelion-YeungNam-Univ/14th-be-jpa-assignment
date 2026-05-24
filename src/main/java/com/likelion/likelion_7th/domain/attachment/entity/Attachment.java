package com.likelion.likelion_7th.domain.attachment.entity;

import com.likelion.likelion_7th.domain.board.entity.Board;
import com.likelion.likelion_7th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(nullable = false, length = 255)
    private String originalName; // 원본 이름

    @Column(nullable = false, length = 255)
    private String storedName; // 저장 이름

    @Column(nullable = false, length = 512)
    private String filePath; // 파일 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Attachment(String originalName, String storedName, String filePath, Board board) {
        this.originalName = originalName;
        this.storedName = storedName;
        this.filePath = filePath;
        this.board = board;
    }
}