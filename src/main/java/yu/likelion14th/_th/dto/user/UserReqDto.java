package yu.likelion14th._th.dto.user;

import lombok.Getter;

// 회원 요청 DTO
@Getter
public class UserReqDto {
    private String username;
    private String useremail;
    private String password;
}
