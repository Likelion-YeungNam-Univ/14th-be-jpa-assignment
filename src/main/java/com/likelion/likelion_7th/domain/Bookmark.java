package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "bookmark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "movie_id"})
)

public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //북마크 고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //북마크한 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //북마크된 영화

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //북마크 생성 일시

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}