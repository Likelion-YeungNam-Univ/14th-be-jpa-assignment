package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.UserReqDto;
import com.likelion.likelion_7th.dto.UserResDto;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDto create(UserReqDto requestDto) {
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .build();

        User savedUser = userRepository.save(user);
        return UserResDto.from(savedUser);
    }
}
