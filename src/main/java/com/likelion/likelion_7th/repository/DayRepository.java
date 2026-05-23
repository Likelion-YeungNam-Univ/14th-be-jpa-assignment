package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    List<Day> findByUserId(Long userId);
}