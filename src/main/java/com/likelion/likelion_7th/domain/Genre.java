package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //장르 고유 ID

    @Column(nullable = false, unique = true, length = 255)
    private String name; //장르명 (중복 불가)

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieGenre> movieGenres = new ArrayList<>(); //이 장르에 연결된 영화 목록
}