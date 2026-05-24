package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.movie.MovieRequestDto;
import com.likelion.likelion_7th.dto.movie.MovieResponseDto;
import com.likelion.likelion_7th.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // [POST] 영화 등록
    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieRequestDto dto) {
        return ResponseEntity.ok(movieService.createMovie(dto));
    }

    // [GET] 전체 영화 조회
    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // [GET] 단일 영화 조회
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    // [PUT]영화 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

    // [DELETE] 영화 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
