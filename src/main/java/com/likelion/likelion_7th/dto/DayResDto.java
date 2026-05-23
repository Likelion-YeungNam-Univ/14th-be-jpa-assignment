package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Day;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DayResDto {
    private Long id;
    private LocalDate dayStart;
    private LocalDate dayEnd;

    public static DayResDto from(Day day) {
        return DayResDto.builder()
                .id(day.getId())
                .dayStart(day.getDayStart())
                .dayEnd(day.getDayEnd())
                .build();
    }
}
