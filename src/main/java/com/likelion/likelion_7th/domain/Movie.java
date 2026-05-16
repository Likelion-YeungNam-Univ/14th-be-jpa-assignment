package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    @Entity
    @Table(name = "movie")
    public class Movie {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id; //영화 고유 ID

        @Column(nullable = false, length = 255)
        private String title; //영화 제목

        @Column(name = "release_date")
        private LocalDate releaseDate; //개봉일

        @Column(columnDefinition = "TEXT")
        private String description; //영화 설명

        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Review> reviews = new ArrayList<>(); //영화 리뷰 목록

        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Rating> ratings = new ArrayList<>(); //영화 별점 목록

        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Bookmark> bookmarks = new ArrayList<>(); //영화 북마크 목록

        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<MovieDirector> movieDirectors = new ArrayList<>(); //영화의 감독 목록

        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<MovieGenre> movieGenres = new ArrayList<>(); //영화의 장르 연결 목록
    }