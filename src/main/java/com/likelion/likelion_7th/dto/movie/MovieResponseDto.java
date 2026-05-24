package com.likelion.likelion_7th.dto.movie;

import com.likelion.likelion_7th.domain.Movie;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class MovieResponseDto {
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String description;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.releaseDate = movie.getReleaseDate();
        this.description = movie.getDescription();
    }
}
