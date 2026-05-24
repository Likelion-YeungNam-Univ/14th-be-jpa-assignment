package com.likelion.likelion_7th.dto.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MovieRequestDto {
    private String title;
    private LocalDate releaseDate;
    private String description;
}
