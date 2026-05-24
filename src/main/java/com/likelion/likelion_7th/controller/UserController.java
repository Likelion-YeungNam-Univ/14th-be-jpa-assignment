package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.user.UserRequestDto;
import com.likelion.likelion_7th.dto.user.UserResponseDto;
import com.likelion.likelion_7th.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // [POST] 회원가입
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    // [GET] 전체 유저 목록 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // [GET] 유저 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    // [PUT] 유저 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // [DELETE] 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
