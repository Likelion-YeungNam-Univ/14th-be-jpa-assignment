package com.likelion.likelion_7th.domain.users.service;

import com.likelion.likelion_7th.domain.users.dto.request.UserCreateRequest;
import com.likelion.likelion_7th.domain.users.dto.request.UserUpdateRequest;
import com.likelion.likelion_7th.domain.users.dto.response.UserResponse;
import com.likelion.likelion_7th.domain.users.entity.User;
import com.likelion.likelion_7th.domain.users.enums.Role;
import com.likelion.likelion_7th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .role(Role.ROLE_USER)
                .build();
        return UserResponse.from(userRepository.save(user));
    }

    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        user.update(request.getName(), request.getPassword());
        return UserResponse.from(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        userRepository.delete(user);
    }
}
