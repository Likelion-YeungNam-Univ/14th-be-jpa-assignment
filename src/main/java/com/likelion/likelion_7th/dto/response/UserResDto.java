package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
// 회원가입 응답 DTO
public class UserResDto {
    private Long id;
    private String email;
    private String nickname;

    public static UserResDto from(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}