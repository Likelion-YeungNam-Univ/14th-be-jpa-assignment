package com.likelion.likelion_7th.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 회원가입 요청 DTO
public class UserReqDto {
    private String email;
    private String password;
    private String nickname;
}