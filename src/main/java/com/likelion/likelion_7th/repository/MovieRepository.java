package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
