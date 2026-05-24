package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity //JPA에게 연결 될 수 있음을 알려주는 기능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자를 보호됨으로 만드는 롬북 명령어, 퍼블릭은 접근가능 프라이빗은 JPA도 보지 못함
@Getter // 세터는 비추천
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 키 값
    @Column(name="id")
    private Long id; //PK

    private Integer like_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @Builder
    public Like(Integer like_count) {
        this.like_count = like_count;
    }

    // 연관관계 편의 메서드
    public void confirmUser(User user) {
        this.user = user;
        if (!user.getLike().contains(this)) {
            user.getLike().add(this);
        }
    }

    public void confirmPost(Post post) {
        this.post = post;
        if (!post.getLike().contains(this)) {
            post.getLike().add(this);
        }
    }

    public void confirmComment(Comment comment) {
        this.comment = comment;
        if (!comment.getLike().contains(this)) {
            comment.getLike().add(this);
        }
    }
}