package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);
}
