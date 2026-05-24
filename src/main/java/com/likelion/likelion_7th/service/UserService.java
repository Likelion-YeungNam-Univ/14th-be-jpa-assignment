package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.request.UserReqDto;
import com.likelion.likelion_7th.dto.response.UserResDto;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public UserResDto createUser(UserReqDto req) {
        User user = User.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .nickname(req.getNickname())
                .build();
        return UserResDto.from(userRepository.save(user));
    }

    // 유저 조회
    public UserResDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        return UserResDto.from(user);
    }

    // 유저 수정
    public UserResDto updateUser(Long userId, UserReqDto req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        User updated = User.builder()
                .id(user.getId())
                .email(user.getEmail()) // 이메일과 아이디는 수정x
                .password(req.getPassword())
                .nickname(req.getNickname())
                .build();
        return UserResDto.from(userRepository.save(updated));
    }

    // 유저 삭제
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}