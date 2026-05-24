package com.likelion.likelion_7th.controller;

import com.likelion.likelion_7th.dto.Req.PostReqDto;
import com.likelion.likelion_7th.dto.Res.PostResDto;
import com.likelion.likelion_7th.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/users/{userId}/posts") // 게시글 작성
    public PostResDto createPost(@PathVariable Long userId, @RequestBody PostReqDto postReqDto) { // userId: 유저 번호, postReqDto: 게시글 정보
        return postService.createPost(userId, postReqDto);
    }

    @GetMapping("/posts/{id}") // 게시글 조회
    public PostResDto getPost(@PathVariable Long id) { // id: 게시글 번호
        return postService.getPostDto(id);
    }

    @GetMapping("/posts") // 모든 게시글 조회
    public List<PostResDto> getAllPosts() { 
        return postService.getAllPosts();
    }

    @PutMapping("/posts/{id}") // 게시글 수정
    public PostResDto updatePost(@PathVariable Long id, @RequestBody PostReqDto postReqDto) { // id: 게시글 번호, postReqDto: 게시글 정보
        return postService.updatePost(id, postReqDto);
    }

    @DeleteMapping("/posts/{id}") // 게시글 삭제
    public void deletePost(@PathVariable Long id) { // id: 게시글 번호
        postService.deletePost(id);
    }
}
