package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.DailyJob;
import com.likelion.likelion_7th.dto.response.DailyJobResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyJobRepository extends JpaRepository<DailyJob, Long> {
    default List<DailyJobResponse> findDailyJobResponseList() {
        return findAll().stream()
                .map(DailyJobResponse::from)
                .toList();
    }

    default DailyJob findDailyJobById(Long dailyJobId) {
        return findById(dailyJobId)
                .orElseThrow(() -> new IllegalArgumentException("할 일을 찾을 수 없음"));
    }

    default DailyJobResponse getDailyJobResponse(Long dailyJobId) {
        return DailyJobResponse.from(findDailyJobById(dailyJobId));
    }
}
