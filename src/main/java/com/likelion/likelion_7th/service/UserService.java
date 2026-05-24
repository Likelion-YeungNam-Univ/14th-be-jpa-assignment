package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.Req.UserReqDto;
import com.likelion.likelion_7th.dto.Res.UserResDto;
import com.likelion.likelion_7th.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService { // 유저 관련 로직
    private final UserRepository userRepository;

    @Transactional
    public UserResDto create(UserReqDto requestDto){ // 유저 생성
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(requestDto.getPassword())
                .email(requestDto.getEmail())
                .build();

        User savedUser = userRepository.save(user); // 유저 저장

        return UserResDto.from(savedUser); // 유저 반환
    }

    public UserResDto getUser(Long id) { // 유저 찾기
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return UserResDto.from(user);
    }

    public List<UserResDto> getAllUsers() { // 모든 유저 찾기
        return userRepository.findAll().stream() // 유저 리포지토리에서 모든 유저 찾기
                .map(UserResDto::from) // 유저를 유저 리스폰스 디티오로 변환
                .collect(Collectors.toList()); // 리스트로 수집
    }

    @Transactional
    public UserResDto update(Long id, UserReqDto requestDto) { // 유저 업데이트
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        
        user.update(requestDto.getUsername(), requestDto.getPassword(), requestDto.getEmail()); // 유저 업데이트
        return UserResDto.from(user); // 유저 반환
    }

    @Transactional
    public void delete(Long id) { // 유저 삭제
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        userRepository.delete(user); // 유저 삭제
    }
}
