package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //감독 고유 ID

    @Column(nullable = false, length = 255)
    private String name; //감독이름

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieDirector> movieDirectors = new ArrayList<>(); //감독이 참여한 영화 목록
}