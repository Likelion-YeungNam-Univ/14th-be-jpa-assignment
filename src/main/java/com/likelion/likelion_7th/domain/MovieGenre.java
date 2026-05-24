package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie_genre")
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //연결된 영화

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre; //연결된 장르
}