package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDto {
    private Long id;
    private String username;

    public static UserResDto from(User user) {
        return UserResDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
