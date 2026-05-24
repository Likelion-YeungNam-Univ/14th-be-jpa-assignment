package yu.likelion14th._th.dto.user;

import lombok.Builder;
import lombok.Getter;
import yu.likelion14th._th.domain.User;

// 회원 응답 DTO
@Getter
public class UserResDto {

    private Long userId;
    private String username;
    private String useremail;

    @Builder
    public UserResDto(Long userId, String username, String useremail) {
        this.userId = userId;
        this.username = username;
        this.useremail = useremail;
    }

    public static UserResDto from(User user) {
        return UserResDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .useremail(user.getUseremail())
                .build();
    }
}
