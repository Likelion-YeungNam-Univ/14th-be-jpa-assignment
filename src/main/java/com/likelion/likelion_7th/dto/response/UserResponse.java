package com.likelion.likelion_7th.dto.response;

import com.likelion.likelion_7th.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String name;

    public static UserResponse from(User user) {
        return new UserResponse(user.getUser_id(), user.getName());
    }
}
