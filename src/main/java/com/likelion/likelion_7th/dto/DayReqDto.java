package com.likelion.likelion_7th.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class DayReqDto {
    private Long userId; // 일정을 생성할 주인의 ID
    private LocalDate dayStart;
    private LocalDate dayEnd;
}