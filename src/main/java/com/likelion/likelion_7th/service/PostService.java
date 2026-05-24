package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Post;
import com.likelion.likelion_7th.domain.ProjectUser;
import com.likelion.likelion_7th.dto.PostRequestDto;
import com.likelion.likelion_7th.dto.PostResponseDto;
import com.likelion.likelion_7th.exception.ResourceNotFoundException;
import com.likelion.likelion_7th.repository.PostRepository;
import com.likelion.likelion_7th.repository.ProjectUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final ProjectUserRepository projectUserRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        ProjectUser projectUser = projectUserRepository.findById(requestDto.getProjectUserId())
                .orElseThrow(() -> new ResourceNotFoundException("ProjectUser를 찾을 수 없습니다: " + requestDto.getProjectUserId()));

        Post post = Post.builder()
                .postTitle(requestDto.getPostTitle())
                .postContent(requestDto.getPostContent())
                .projectUser(projectUser)
                .build();

        return new PostResponseDto(postRepository.save(post));
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다: " + id));
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다: " + id));
        post.update(requestDto.getPostTitle(), requestDto.getPostContent());
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다: " + id));
        postRepository.delete(post);
    }
}
