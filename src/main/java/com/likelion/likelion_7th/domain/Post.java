package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //JPA에게 연결 될 수 있음을 알려주는 기능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자를 보호됨으로 만드는 롬북 명령어, 퍼블릭은 접근가능 프라이빗은 JPA도 보지 못함
@Getter // 세터는 비추천
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 키 값
    @Column(name="id")
    private Long id; //PK

    private String head;
    private String body;
    private Long date;

    @Builder
    public Post(String head, String body, Long date) {
        this.head = head;
        this.body = body;
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Like> like;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Category> category;
}
