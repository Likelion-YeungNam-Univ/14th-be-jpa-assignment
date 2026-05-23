package com.likelion.likelion_7th.controller;


import com.likelion.likelion_7th.dto.UserReqDto;
import com.likelion.likelion_7th.dto.UserResDto;
import com.likelion.likelion_7th.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResDto> createUser(@RequestBody UserReqDto requestDto) {
        return ResponseEntity.ok(userService.create(requestDto));
    }
}
