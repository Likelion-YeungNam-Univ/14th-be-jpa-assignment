package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Subtask;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubtaskResDto {
    private Long id;
    private String subtaskTitle;
    private Boolean subtaskStatus;

    public static SubtaskResDto from(Subtask subtask) {
        return SubtaskResDto.builder()
                .id(subtask.getId())
                .subtaskTitle(subtask.getSubtaskTitle())
                .subtaskStatus(subtask.getSubtaskStatus())
                .build();
    }
}
