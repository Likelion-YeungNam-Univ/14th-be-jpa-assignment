package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.review.ReviewRequestDto;
import com.likelion.likelion_7th.dto.review.ReviewResponseDto;
import com.likelion.likelion_7th.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // [POST] 특정 영화에 리뷰 작성
    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable Long movieId, @RequestBody ReviewRequestDto dto) {
        return ResponseEntity.ok(reviewService.createReview(movieId, dto));
    }

    // [GET] 특정 영화의 리뷰 목록 조회
    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }

    // [GET] 리뷰 단건 조회
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    // [PUT] 리뷰 수정
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long id,
                                                          @RequestBody ReviewRequestDto dto) {
        return ResponseEntity.ok(reviewService.updateReview(id, dto));
    }

    // [DELETE] 리뷰 삭제
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
