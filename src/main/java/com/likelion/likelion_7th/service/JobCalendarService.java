package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.DailyJob;
import com.likelion.likelion_7th.domain.JobCalendar;
import com.likelion.likelion_7th.dto.request.JobCalendarRequest;
import com.likelion.likelion_7th.dto.request.JobCalendarUpdateRequest;
import com.likelion.likelion_7th.dto.response.JobCalendarResponse;
import com.likelion.likelion_7th.repository.DailyJobRepository;
import com.likelion.likelion_7th.repository.JobCalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCalendarService {
    private final JobCalendarRepository jobCalendarRepository;
    private final DailyJobRepository dailyJobRepository;

    @Transactional
    public JobCalendarResponse createJobCalendar(JobCalendarRequest request) {
        DailyJob dailyJob = dailyJobRepository.findDailyJobById(request.getDailyJobId());

        JobCalendar jobCalendar = JobCalendar.builder()
                .date(request.getDate())
                .implemented(request.isImplemented())
                .build();
        dailyJob.assignJobCalendar(jobCalendar);

        JobCalendar savedJobCalendar = jobCalendarRepository.save(jobCalendar);
        return JobCalendarResponse.from(savedJobCalendar);
    }

    @Transactional(readOnly = true)
    public List<JobCalendarResponse> getJobCalendarList() {
        return jobCalendarRepository.findJobCalendarResponseList();
    }

    @Transactional(readOnly = true)
    public JobCalendarResponse getJobCalendar(Long jobCalendarId) {
        return jobCalendarRepository.getJobCalendarResponse(jobCalendarId);
    }

    @Transactional
    public JobCalendarResponse updateJobCalendar(Long jobCalendarId, JobCalendarUpdateRequest request) {
        JobCalendar jobCalendar = jobCalendarRepository.findJobCalendarById(jobCalendarId);
        jobCalendar.updateJobCalendar(request.getDate(), request.isImplemented());

        return JobCalendarResponse.from(jobCalendar);
    }

    @Transactional
    public void deleteJobCalendar(Long jobCalendarId) {
        jobCalendarRepository.delete(jobCalendarRepository.findJobCalendarById(jobCalendarId));
    }
}
