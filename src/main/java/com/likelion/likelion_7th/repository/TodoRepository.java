package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByDayId(Long dayId);
}
