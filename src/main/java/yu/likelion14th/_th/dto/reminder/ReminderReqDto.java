package yu.likelion14th._th.dto.reminder;

import lombok.Getter;

// 알림 요청 DTO
@Getter
public class ReminderReqDto {
    private Long todoId;
    private Boolean isSent;
}
