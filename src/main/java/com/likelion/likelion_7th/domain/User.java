package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //유저 고유 ID

    @Column(nullable = false, unique = true, length = 255)
    private String username; // 유저 이름

    @Column(nullable = false, unique = true, length = 255)
    private String email; //이메일

    @Column(nullable = false, length = 60)
    private String password; //비밀번호 (BCrypt 해시)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; //가입 일시

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>(); //작성한 리뷰 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); //작성한 댓글 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>(); //남긴 별점 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>(); //북마크한 영화 목록

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}