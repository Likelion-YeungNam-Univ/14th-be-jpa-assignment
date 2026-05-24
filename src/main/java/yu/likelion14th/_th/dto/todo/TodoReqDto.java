package yu.likelion14th._th.dto.todo;

import lombok.Getter;

// 할 일 요청 DTO
@Getter
public class TodoReqDto {
    private String content;
    private Long userId;
    private Long categoryId;
    private Long priorityId;
}
