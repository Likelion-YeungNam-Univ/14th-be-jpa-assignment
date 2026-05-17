package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 60) // 해시 암호화를 고려해 60자로 설정
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Day> days = new ArrayList<>();

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 연관관계 편의 메서드
    public void addDay(Day day) {
        this.days.add(day);
        day.setUser(this);
    }
}