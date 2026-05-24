package yu.likelion14th._th.dto.reminder;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th._th.domain.Reminder;

// 알림 응답 DTO
@Getter
public class ReminderResDto {

    private Long reminderId;
    private Boolean isSent;
    private Long todoId;

    @Builder
    public ReminderResDto(Long reminderId, Boolean isSent, Long todoId) {
        this.reminderId = reminderId;
        this.isSent = isSent;
        this.todoId = todoId;
    }

    public static ReminderResDto from(Reminder reminder) {
        return ReminderResDto.builder()
                .reminderId(reminder.getReminderId())
                .isSent(reminder.getIsSent())
                .todoId(reminder.getTodo().getTodoId())
                .build();
    }
}
