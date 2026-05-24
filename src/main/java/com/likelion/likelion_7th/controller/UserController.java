package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.Req.UserReqDto;
import com.likelion.likelion_7th.dto.Res.UserResDto;
import com.likelion.likelion_7th.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping // 유저 생성
    public UserResDto createUser(@RequestBody UserReqDto userReqDto) { // userReqDto: 유저 정보
        return userService.create(userReqDto);
    }

    @GetMapping("/{id}") // 유저 조회
    public UserResDto getUser(@PathVariable Long id) { // id: 유저 번호
        return userService.getUser(id);
    }

    @GetMapping // 모든 유저 조회
    public List<UserResDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}") // 유저 수정
    public UserResDto updateUser(@PathVariable Long id, @RequestBody UserReqDto userReqDto) { // id: 유저 번호, userReqDto: 유저 정보
        return userService.update(id, userReqDto);
    }

    @DeleteMapping("/{id}") // 유저 삭제
    public void deleteUser(@PathVariable Long id) { // id: 유저 번호
        userService.delete(id);
    }
}
