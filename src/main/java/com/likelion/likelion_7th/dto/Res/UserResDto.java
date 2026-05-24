package com.likelion.likelion_7th.dto.Res;

import com.likelion.likelion_7th.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResDto {

    private Long id;
    private String username;
    private String email;

    @Builder
    public UserResDto(Long id, String username, String email) { // 유저 응답 디티오 생성
        this.id = id; // 유저 아이디
        this.username = username; // 유저 이름
        this.email = email; // 유저 이메일
    }

    public static UserResDto from(User user) { // 유저를 유저 응답 디티오로 변환
        return UserResDto.builder()
                .id(user.getId()) // 유저 아이디
                .username(user.getUsername()) // 유저 이름
                .email(user.getEmail()) // 유저 이메일
                .build(); // 유저 응답 디티오 빌더
    }
}
