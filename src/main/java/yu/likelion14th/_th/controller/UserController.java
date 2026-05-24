package yu.likelion14th._th.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th._th.dto.user.UserReqDto;
import yu.likelion14th._th.dto.user.UserResDto;
import yu.likelion14th._th.service.UserService;

import java.util.List;

// 회원 컨트롤러
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 생성
    @PostMapping
    public UserResDto create(@RequestBody UserReqDto requestDto) {
        return userService.create(requestDto);
    }

    // 전체 회원 조회
    @GetMapping
    public List<UserResDto> findAll() {
        return userService.findAll();
    }

    // 단일 회원 조회
    @GetMapping("/{userId}")
    public UserResDto findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    // 회원 정보 수정
    @PutMapping("/{userId}")
    public UserResDto update(@PathVariable Long userId,
                             @RequestBody UserReqDto requestDto) {
        return userService.update(userId, requestDto);
    }

    // 회원 삭제
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
