package com.likelion.likelion_7th.dto.review;

import com.likelion.likelion_7th.domain.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private String content;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.userId = review.getUser().getId();
        this.movieId = review.getMovie().getId();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }
}
