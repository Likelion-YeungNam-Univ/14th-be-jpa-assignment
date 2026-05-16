package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
// 한 유저가 같은 영화에 리뷰 1개만 가능
@Table(
        name = "review",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"})
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //리뷰 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //리뷰 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //리뷰 대상 영화

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; //리뷰 내용

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //작성 일시

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); //리뷰에 달린 댓글 목록

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); //저장 시 현재 시간 자동 설정
    }
}