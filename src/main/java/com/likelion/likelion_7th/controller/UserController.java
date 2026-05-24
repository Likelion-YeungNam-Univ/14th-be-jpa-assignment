package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.request.UserReqDto;
import com.likelion.likelion_7th.dto.response.UserResDto;
import com.likelion.likelion_7th.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// User 요청 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping
    public ResponseEntity<UserResDto> createUser(@RequestBody UserReqDto req) {
        return ResponseEntity.ok(userService.createUser(req));
    }

    // 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // 유저 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResDto> updateUser(@PathVariable Long userId, @RequestBody UserReqDto req) {
        return ResponseEntity.ok(userService.updateUser(userId, req));
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}