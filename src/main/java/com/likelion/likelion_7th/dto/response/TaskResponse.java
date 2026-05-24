package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TaskResponse {
    private Long taskId;
    private Long userId;
    private Long subjectId;
    private String name;
    private String content;
    private LocalDate deadline;
    private boolean status;

    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getTask_id(),
                task.getUser().getUser_id(),
                task.getSubject().getSubject_id(),
                task.getName(),
                task.getContent(),
                task.getDeadline(),
                task.isStatus()
        );
    }
}
