package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Movie;
import com.likelion.likelion_7th.domain.Review;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.review.ReviewRequestDto;
import com.likelion.likelion_7th.dto.review.ReviewResponseDto;
import com.likelion.likelion_7th.repository.MovieRepository;
import com.likelion.likelion_7th.repository.ReviewRepository;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    // 리뷰 작성
    @Transactional
    public ReviewResponseDto createReview(Long movieId, ReviewRequestDto dto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("영화를 찾을 수 없습니다. id=" + movieId));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + dto.getUserId()));

        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setContent(dto.getContent());
        return new ReviewResponseDto(reviewRepository.save(review));
    }

    // 특정 영화의 리뷰 목록 조회
    public List<ReviewResponseDto> getReviewsByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId).stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    // 리뷰 단건 조회
    public ReviewResponseDto getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. id=" + id));
        return new ReviewResponseDto(review);
    }

    // 리뷰 수정
    @Transactional
    public ReviewResponseDto updateReview(Long id, ReviewRequestDto dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다. id=" + id));
        review.setContent(dto.getContent());
        return new ReviewResponseDto(review);
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
