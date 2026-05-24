package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.user.UserRequestDto;
import com.likelion.likelion_7th.dto.user.UserResponseDto;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return new UserResponseDto(userRepository.save(user));
    }

    // 전체 유저 목록 조회
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저 정보 단건 조회
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + id));
        return new UserResponseDto(user);
    }

    // 유저 정보 수정
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다. id=" + id));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return new UserResponseDto(user);
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
