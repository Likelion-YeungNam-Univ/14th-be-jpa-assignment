package yu.likelion14th._th.dto.todo;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th._th.domain.Todo;

// 할 일 응답 DTO
@Getter
public class TodoResDto {

    private Long todoId;
    private String content;
    private Boolean isDone;
    private Long userId;
    private Long categoryId;
    private Long priorityId;

    @Builder
    public TodoResDto(Long todoId, String content, Boolean isDone,
                      Long userId, Long categoryId, Long priorityId) {
        this.todoId = todoId;
        this.content = content;
        this.isDone = isDone;
        this.userId = userId;
        this.categoryId = categoryId;
        this.priorityId = priorityId;
    }

    public static TodoResDto from(Todo todo) {
        return TodoResDto.builder()
                .todoId(todo.getTodoId())
                .content(todo.getContent())
                .isDone(todo.getIsDone())
                .userId(todo.getUser().getUserId())
                .categoryId(todo.getCategory().getCategoryId())
                .priorityId(todo.getPriority().getPriorityId())
                .build();
    }
}
