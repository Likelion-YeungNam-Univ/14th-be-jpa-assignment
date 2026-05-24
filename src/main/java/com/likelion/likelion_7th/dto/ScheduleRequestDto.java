package com.likelion.likelion_7th.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "일정 이름은 필수입니다.")
    private String scheduleName;

    private String scheduleMemo;

    @NotNull(message = "시작 시간은 필수입니다.")
    private LocalDateTime scheduleStart;

    @NotNull(message = "종료 시간은 필수입니다.")
    private LocalDateTime scheduleEnd;

    private String scheduleType;
}
