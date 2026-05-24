package com.likelion.likelion_7th.repository;

import com.likelion.likelion_7th.domain.JobCalendar;
import com.likelion.likelion_7th.dto.response.JobCalendarResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCalendarRepository extends JpaRepository<JobCalendar, Long> {
    default List<JobCalendarResponse> findJobCalendarResponseList() {
        return findAll().stream()
                .map(JobCalendarResponse::from)
                .toList();
    }

    default JobCalendar findJobCalendarById(Long jobCalendarId) {
        return findById(jobCalendarId)
                .orElseThrow(() -> new IllegalArgumentException("할 일 수행표를 찾을 수 없음"));
    }

    default JobCalendarResponse getJobCalendarResponse(Long jobCalendarId) {
        return JobCalendarResponse.from(findJobCalendarById(jobCalendarId));
    }
}
