package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.JobCalendarRequest;
import com.likelion.likelion_7th.dto.request.JobCalendarUpdateRequest;
import com.likelion.likelion_7th.dto.response.JobCalendarResponse;
import com.likelion.likelion_7th.service.JobCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-calendars")
public class JobCalendarController {
    private final JobCalendarService jobCalendarService;

    @PostMapping
    public ResponseEntity<JobCalendarResponse> createJobCalendar(@RequestBody JobCalendarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCalendarService.createJobCalendar(request));
    }

    @GetMapping
    public ResponseEntity<List<JobCalendarResponse>> getJobCalendarList() {
        return ResponseEntity.ok(jobCalendarService.getJobCalendarList());
    }

    @GetMapping("/{jobCalendarId}")
    public ResponseEntity<JobCalendarResponse> getJobCalendar(@PathVariable Long jobCalendarId) {
        return ResponseEntity.ok(jobCalendarService.getJobCalendar(jobCalendarId));
    }

    @PatchMapping("/{jobCalendarId}")
    public ResponseEntity<JobCalendarResponse> updateJobCalendar(
            @PathVariable Long jobCalendarId,
            @RequestBody JobCalendarUpdateRequest request
    ) {
        return ResponseEntity.ok(jobCalendarService.updateJobCalendar(jobCalendarId, request));
    }

    @DeleteMapping("/{jobCalendarId}")
    public ResponseEntity<Void> deleteJobCalendar(@PathVariable Long jobCalendarId) {
        jobCalendarService.deleteJobCalendar(jobCalendarId);
        return ResponseEntity.noContent().build();
    }
}
