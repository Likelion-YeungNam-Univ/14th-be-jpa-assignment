package com.likelion.likelion_7th.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubtaskReqDto {
    private Long todoId;
    private String subtaskTitle;
}
