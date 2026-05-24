package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Movie;
import com.likelion.likelion_7th.dto.movie.MovieRequestDto;
import com.likelion.likelion_7th.dto.movie.MovieResponseDto;
import com.likelion.likelion_7th.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    // 영화 등록
    @Transactional
    public MovieResponseDto createMovie(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setDescription(dto.getDescription());
        return new MovieResponseDto(movieRepository.save(movie));
    }

    // 전체 영화 목록 조회
    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieResponseDto::new)
                .collect(Collectors.toList());
    }

    // 영화 단건 조회
    public MovieResponseDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다. id=" + id));
        return new MovieResponseDto(movie);
    }

    // 영화 정보 수정
    @Transactional
    public MovieResponseDto updateMovie(Long id, MovieRequestDto dto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다. id=" + id));
        movie.setTitle(dto.getTitle());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setDescription(dto.getDescription());
        return new MovieResponseDto(movie);
    }

    // 영화 삭제
    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
