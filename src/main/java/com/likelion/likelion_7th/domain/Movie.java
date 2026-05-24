package com.likelion.likelion_7th.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieDirector> movieDirectors = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieGenre> movieGenres = new ArrayList<>();
}
