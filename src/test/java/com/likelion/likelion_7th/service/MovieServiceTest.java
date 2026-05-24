package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.dto.movie.MovieRequestDto;
import com.likelion.likelion_7th.dto.movie.MovieResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    private MovieRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new MovieRequestDto();
        requestDto.setTitle("인터스텔라");
        requestDto.setReleaseDate(LocalDate.of(2014, 11, 6));
        requestDto.setDescription("우주 탐험 영화");
    }

    @Test
    @DisplayName("영화 등록 후 DB에 저장되는지 확인")
    void createMovie() {
        MovieResponseDto result = movieService.createMovie(requestDto);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getTitle()).isEqualTo("인터스텔라");
        assertThat(result.getDescription()).isEqualTo("우주 탐험 영화");
    }

    @Test
    @DisplayName("영화 2개 저장 후 전체 목록 조회")
    void getAllMovies() {
        MovieRequestDto dto2 = new MovieRequestDto();
        dto2.setTitle("어벤져스");
        dto2.setReleaseDate(LocalDate.of(2012, 4, 26));
        dto2.setDescription("마블 영화");

        movieService.createMovie(requestDto);
        movieService.createMovie(dto2);

        List<MovieResponseDto> result = movieService.getAllMovies();

        assertThat(result).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("영화 저장 후 ID로 단건 조회")
    void getMovie() {
        MovieResponseDto saved = movieService.createMovie(requestDto);

        MovieResponseDto result = movieService.getMovie(saved.getId());

        assertThat(result.getId()).isEqualTo(saved.getId());
        assertThat(result.getTitle()).isEqualTo("인터스텔라");
    }

    @Test
    @DisplayName("존재하지 않는 ID 조회 시 예외 발생")
    void getMovie_notFound() {
        assertThatThrownBy(() -> movieService.getMovie(9999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("영화를 찾을 수 없습니다");
    }

    @Test
    @DisplayName("영화 정보 수정 후 DB에 반영되는지 확인")
    void updateMovie() {
        MovieResponseDto saved = movieService.createMovie(requestDto);

        MovieRequestDto updateDto = new MovieRequestDto();
        updateDto.setTitle("수정된 제목");
        updateDto.setReleaseDate(LocalDate.of(2020, 1, 1));
        updateDto.setDescription("수정된 설명");

        MovieResponseDto result = movieService.updateMovie(saved.getId(), updateDto);

        assertThat(result.getTitle()).isEqualTo("수정된 제목");
        assertThat(result.getDescription()).isEqualTo("수정된 설명");
    }

    @Test
    @DisplayName("영화 삭제 후 조회 시 예외 발생")
    void deleteMovie() {
        MovieResponseDto saved = movieService.createMovie(requestDto);
        Long savedId = saved.getId();

        movieService.deleteMovie(savedId);

        assertThatThrownBy(() -> movieService.getMovie(savedId))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
