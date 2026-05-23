package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    List<Subtask> findByTodoId(Long todoId);
}
