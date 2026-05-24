package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final Long id;
    private final String userId;
    private final String name;
    private final String email;
    private final String phone;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
