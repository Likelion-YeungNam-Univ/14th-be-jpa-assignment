package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.UserRequest;
import com.likelion.likelion_7th.dto.response.UserResponse;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .password(request.getPassword())
                .build();

        User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUserList() {
        return userRepository.findUserResponseList();
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId) {
        return userRepository.getUserResponse(userId);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.delete(userRepository.findUserById(userId));
    }
}
