package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.DailyJobRequest;
import com.likelion.likelion_7th.dto.request.DailyJobUpdateRequest;
import com.likelion.likelion_7th.dto.response.DailyJobResponse;
import com.likelion.likelion_7th.service.DailyJobService;
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
@RequestMapping("/daily-jobs")
public class DailyJobController {
    private final DailyJobService dailyJobService;

    @PostMapping
    public ResponseEntity<DailyJobResponse> createDailyJob(@RequestBody DailyJobRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dailyJobService.createDailyJob(request));
    }

    @GetMapping
    public ResponseEntity<List<DailyJobResponse>> getDailyJobList() {
        return ResponseEntity.ok(dailyJobService.getDailyJobList());
    }

    @GetMapping("/{dailyJobId}")
    public ResponseEntity<DailyJobResponse> getDailyJob(@PathVariable Long dailyJobId) {
        return ResponseEntity.ok(dailyJobService.getDailyJob(dailyJobId));
    }

    @PatchMapping("/{dailyJobId}")
    public ResponseEntity<DailyJobResponse> updateDailyJob(@PathVariable Long dailyJobId, @RequestBody DailyJobUpdateRequest request) {
        return ResponseEntity.ok(dailyJobService.updateDailyJob(dailyJobId, request));
    }

    @DeleteMapping("/{dailyJobId}")
    public ResponseEntity<Void> deleteDailyJob(@PathVariable Long dailyJobId) {
        dailyJobService.deleteDailyJob(dailyJobId);
        return ResponseEntity.noContent().build();
    }
}
