package yu.likelion14th._th.dto.priority;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th._th.domain.Priority;

// 우선순위 응답 DTO
@Getter
public class PriorityResDto {

    private Long priorityId;
    private String levelName;

    @Builder
    public PriorityResDto(Long priorityId, String levelName) {
        this.priorityId = priorityId;
        this.levelName = levelName;
    }

    public static PriorityResDto from(Priority priority) {
        return PriorityResDto.builder()
                .priorityId(priority.getPriorityId())
                .levelName(priority.getLevelName())
                .build();
    }
}
