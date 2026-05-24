package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.dto.movie.MovieRequestDto;
import com.likelion.likelion_7th.dto.movie.MovieResponseDto;
import com.likelion.likelion_7th.dto.review.ReviewRequestDto;
import com.likelion.likelion_7th.dto.review.ReviewResponseDto;
import com.likelion.likelion_7th.dto.user.UserRequestDto;
import com.likelion.likelion_7th.dto.user.UserResponseDto;
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
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private UserService userService;

    private Long movieId;
    private Long userId;
    private ReviewRequestDto requestDto;

    @BeforeEach
    void setUp() {
        MovieRequestDto movieDto = new MovieRequestDto();
        movieDto.setTitle("인터스텔라");
        movieDto.setReleaseDate(LocalDate.of(2014, 11, 6));
        movieDto.setDescription("우주 탐험 영화");
        MovieResponseDto savedMovie = movieService.createMovie(movieDto);
        movieId = savedMovie.getId();

        UserRequestDto userDto = new UserRequestDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@test.com");
        userDto.setPassword("password123");
        UserResponseDto savedUser = userService.createUser(userDto);
        userId = savedUser.getId();

        requestDto = new ReviewRequestDto();
        requestDto.setUserId(userId);
        requestDto.setContent("정말 재밌어요!");
    }

    @Test
    @DisplayName("리뷰 작성 후 DB에 저장되는지 확인")
    void createReview() {
        ReviewResponseDto result = reviewService.createReview(movieId, requestDto);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getContent()).isEqualTo("정말 재밌어요!");
        assertThat(result.getMovieId()).isEqualTo(movieId);
        assertThat(result.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("존재하지 않는 영화에 리뷰 작성 시 예외 발생")
    void createReview_movieNotFound() {
        assertThatThrownBy(() -> reviewService.createReview(9999L, requestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("영화를 찾을 수 없습니다");
    }

    @Test
    @DisplayName("특정 영화의 리뷰 목록 조회")
    void getReviewsByMovie() {
        ReviewRequestDto dto2 = new ReviewRequestDto();
        dto2.setUserId(userId);
        dto2.setContent("두 번째 리뷰");

        reviewService.createReview(movieId, requestDto);

        List<ReviewResponseDto> result = reviewService.getReviewsByMovie(movieId);

        assertThat(result).hasSizeGreaterThanOrEqualTo(1);
        assertThat(result.get(0).getMovieId()).isEqualTo(movieId);
    }

    @Test
    @DisplayName("리뷰 단건 조회")
    void getReview() {
        ReviewResponseDto saved = reviewService.createReview(movieId, requestDto);

        ReviewResponseDto result = reviewService.getReview(saved.getId());

        assertThat(result.getId()).isEqualTo(saved.getId());
        assertThat(result.getContent()).isEqualTo("정말 재밌어요!");
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 조회 시 예외 발생")
    void getReview_notFound() {
        assertThatThrownBy(() -> reviewService.getReview(9999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("리뷰를 찾을 수 없습니다");
    }

    @Test
    @DisplayName("리뷰 수정 후 DB에 반영되는지 확인")
    void updateReview() {
        ReviewResponseDto saved = reviewService.createReview(movieId, requestDto);

        ReviewRequestDto updateDto = new ReviewRequestDto();
        updateDto.setContent("수정된 리뷰 내용");

        ReviewResponseDto result = reviewService.updateReview(saved.getId(), updateDto);

        assertThat(result.getContent()).isEqualTo("수정된 리뷰 내용");
    }

    @Test
    @DisplayName("리뷰 삭제 후 조회 시 예외 발생")
    void deleteReview() {
        ReviewResponseDto saved = reviewService.createReview(movieId, requestDto);
        Long savedId = saved.getId();

        reviewService.deleteReview(savedId);

        assertThatThrownBy(() -> reviewService.getReview(savedId))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
