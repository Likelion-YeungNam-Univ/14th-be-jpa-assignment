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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 키 값
    @Column(name="id")
    private Long id; //PK

    private String username;
    private String password;
    private String email;

    @Builder
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void update(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> like = new ArrayList<>();
}
