package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.Task;
import com.likelion.likelion_7th.dto.response.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    default List<TaskResponse> findTaskResponseList() {
        return findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }

    default Task findTaskById(Long taskId) {
        return findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("과제를 찾을 수 없음"));
    }

    default TaskResponse getTaskResponse(Long taskId) {
        return TaskResponse.from(findTaskById(taskId));
    }
}
