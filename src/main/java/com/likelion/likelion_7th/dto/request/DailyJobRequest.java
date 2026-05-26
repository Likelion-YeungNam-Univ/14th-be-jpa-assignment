package com.likelion.likelion_7th.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyJobRequest {
    private Long userId;
    private String content;
    private boolean implement;
}
