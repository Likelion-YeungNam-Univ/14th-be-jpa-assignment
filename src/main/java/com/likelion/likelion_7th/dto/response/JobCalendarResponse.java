package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.JobCalendar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class JobCalendarResponse {
    private Long calendarId;
    private Long dailyJobId;
    private LocalDate date;
    private boolean implemented;

    public static JobCalendarResponse from(JobCalendar jobCalendar) {
        return new JobCalendarResponse(
                jobCalendar.getCalendar_id(),
                jobCalendar.getDailyJob().getJob_id(),
                jobCalendar.getDate(),
                jobCalendar.isImplemented()
        );
    }
}
