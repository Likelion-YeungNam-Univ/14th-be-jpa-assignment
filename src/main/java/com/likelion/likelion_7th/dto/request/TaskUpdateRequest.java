package com.likelion.likelion_7th.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskUpdateRequest {
    private LocalDate deadline;
    private boolean status;
}
