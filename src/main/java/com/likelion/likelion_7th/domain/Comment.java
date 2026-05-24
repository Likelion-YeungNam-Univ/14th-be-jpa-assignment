package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity //JPA에게 연결 될 수 있음을 알려주는 기능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자를 보호됨으로 만드는 롬북 명령어, 퍼블릭은 접근가능 프라이빗은 JPA도 보지 못함
@Getter // 세터는 비추천
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 키 값
    @Column(name="id")
    private Long id; //PK

    private String comment_body;

    @Builder
    public Comment(String comment_body) {
        this.comment_body = comment_body;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> like = new ArrayList<>();

    // 연관관계 편의 메서드
    public void confirmUser(User user) {
        this.user = user;
        if (!user.getComments().contains(this)) {
            user.getComments().add(this);
        }
    }

    public void confirmPost(Post post) {
        this.post = post;
        if (!post.getComments().contains(this)) {
            post.getComments().add(this);
        }
    }

    // 수정 메서드
    public void update(String comment_body) {
        this.comment_body = comment_body;
    }
}
