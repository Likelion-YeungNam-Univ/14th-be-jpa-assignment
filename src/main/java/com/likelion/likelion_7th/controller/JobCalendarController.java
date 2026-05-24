package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.JobCalendarRequest;
import com.likelion.likelion_7th.dto.request.JobCalendarUpdateRequest;
import com.likelion.likelion_7th.dto.response.JobCalendarResponse;
import com.likelion.likelion_7th.service.JobCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-calendars")
public class JobCalendarController {
    private final JobCalendarService jobCalendarService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobCalendarResponse createJobCalendar(@RequestBody JobCalendarRequest request) {
        return jobCalendarService.createJobCalendar(request);
    }

    @GetMapping
    public List<JobCalendarResponse> getJobCalendarList() {
        return jobCalendarService.getJobCalendarList();
    }

    @GetMapping("/{jobCalendarId}")
    public JobCalendarResponse getJobCalendar(@PathVariable Long jobCalendarId) {
        return jobCalendarService.getJobCalendar(jobCalendarId);
    }

    @PatchMapping("/{jobCalendarId}")
    public JobCalendarResponse updateJobCalendar(
            @PathVariable Long jobCalendarId,
            @RequestBody JobCalendarUpdateRequest request
    ) {
        return jobCalendarService.updateJobCalendar(jobCalendarId, request);
    }

    @DeleteMapping("/{jobCalendarId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobCalendar(@PathVariable Long jobCalendarId) {
        jobCalendarService.deleteJobCalendar(jobCalendarId);
    }
}
