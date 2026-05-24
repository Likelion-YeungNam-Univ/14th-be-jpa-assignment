package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.DailyJobRequest;
import com.likelion.likelion_7th.dto.request.DailyJobUpdateRequest;
import com.likelion.likelion_7th.dto.response.DailyJobResponse;
import com.likelion.likelion_7th.service.DailyJobService;
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
@RequestMapping("/daily-jobs")
public class DailyJobController {
    private final DailyJobService dailyJobService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DailyJobResponse createDailyJob(@RequestBody DailyJobRequest request) {
        return dailyJobService.createDailyJob(request);
    }

    @GetMapping
    public List<DailyJobResponse> getDailyJobList() {
        return dailyJobService.getDailyJobList();
    }

    @GetMapping("/{dailyJobId}")
    public DailyJobResponse getDailyJob(@PathVariable Long dailyJobId) {
        return dailyJobService.getDailyJob(dailyJobId);
    }

    @PatchMapping("/{dailyJobId}")
    public DailyJobResponse updateDailyJob(@PathVariable Long dailyJobId, @RequestBody DailyJobUpdateRequest request) {
        return dailyJobService.updateDailyJob(dailyJobId, request);
    }

    @DeleteMapping("/{dailyJobId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDailyJob(@PathVariable Long dailyJobId) {
        dailyJobService.deleteDailyJob(dailyJobId);
    }
}
