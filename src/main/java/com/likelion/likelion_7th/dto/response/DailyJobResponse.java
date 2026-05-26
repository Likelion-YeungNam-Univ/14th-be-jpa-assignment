package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.DailyJob;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyJobResponse {
    private Long dailyJobId;
    private Long userId;
    private String content;
    private boolean implement;

    public static DailyJobResponse from(DailyJob dailyJob) {
        return new DailyJobResponse(
                dailyJob.getJob_id(),
                dailyJob.getUser().getUser_id(),
                dailyJob.getContent(),
                dailyJob.isImplement()
        );
    }
}
