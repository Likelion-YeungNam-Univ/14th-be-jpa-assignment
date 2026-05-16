package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //댓글 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review; //댓글이 달린 리뷰

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //댓글 작성자

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; //댓글 내용

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //작성일시

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 현재 작성 시간 설정
    }
}