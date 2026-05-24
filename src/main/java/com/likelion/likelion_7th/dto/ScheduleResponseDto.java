package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String scheduleName;
    private final String scheduleMemo;
    private final LocalDateTime scheduleStart;
    private final LocalDateTime scheduleEnd;
    private final String scheduleType;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleName = schedule.getScheduleName();
        this.scheduleMemo = schedule.getScheduleMemo();
        this.scheduleStart = schedule.getScheduleStart();
        this.scheduleEnd = schedule.getScheduleEnd();
        this.scheduleType = schedule.getScheduleType();
    }
}
