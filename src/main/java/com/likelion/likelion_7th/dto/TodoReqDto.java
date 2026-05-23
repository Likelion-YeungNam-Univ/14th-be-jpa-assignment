package com.likelion.likelion_7th.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoReqDto {
    private Long dayId;
    private String todoTitle;
    private String todoContent;
}
