package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.dto.user.UserRequestDto;
import com.likelion.likelion_7th.dto.user.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    private UserRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new UserRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setEmail("test@test.com");
        requestDto.setPassword("password123");
    }

    @Test
    @DisplayName("회원가입 후 DB에 저장되는지 확인")
    void createUser() {
        UserResponseDto result = userService.createUser(requestDto);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("유저 2명 저장 후 전체 목록 조회")
    void getAllUsers() {
        UserRequestDto dto2 = new UserRequestDto();
        dto2.setUsername("user2");
        dto2.setEmail("user2@test.com");
        dto2.setPassword("pass2");

        userService.createUser(requestDto);
        userService.createUser(dto2);

        List<UserResponseDto> result = userService.getAllUsers();

        assertThat(result).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("유저 저장 후 ID로 단건 조회")
    void getUser() {
        UserResponseDto saved = userService.createUser(requestDto);

        UserResponseDto result = userService.getUser(saved.getId());

        assertThat(result.getId()).isEqualTo(saved.getId());
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }

    @Test
    @DisplayName("존재하지 않는 유저 조회 시 예외 발생")
    void getUser_notFound() {
        assertThatThrownBy(() -> userService.getUser(9999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유저를 찾을 수 없습니다");
    }

    @Test
    @DisplayName("유저 정보 수정 후 DB에 반영되는지 확인")
    void updateUser() {
        UserResponseDto saved = userService.createUser(requestDto);

        UserRequestDto updateDto = new UserRequestDto();
        updateDto.setUsername("newname");
        updateDto.setEmail("new@test.com");
        updateDto.setPassword("newpassword");

        UserResponseDto result = userService.updateUser(saved.getId(), updateDto);

        assertThat(result.getUsername()).isEqualTo("newname");
        assertThat(result.getEmail()).isEqualTo("new@test.com");
    }

    @Test
    @DisplayName("회원 탈퇴 후 조회 시 예외 발생")
    void deleteUser() {
        UserResponseDto saved = userService.createUser(requestDto);
        Long savedId = saved.getId();

        userService.deleteUser(savedId);

        assertThatThrownBy(() -> userService.getUser(savedId))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
