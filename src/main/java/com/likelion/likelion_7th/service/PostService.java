package com.likelion.likelion_7th.service;

import com.likelion.likelion_7th.domain.Category;
import com.likelion.likelion_7th.domain.Post;
import com.likelion.likelion_7th.domain.User;
import com.likelion.likelion_7th.dto.Req.PostReqDto;
import com.likelion.likelion_7th.dto.Res.PostResDto;
import com.likelion.likelion_7th.repository.PostRepository;
import com.likelion.likelion_7th.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public PostResDto createPost(Long userId, PostReqDto postReqDto) { // 유저 찾기
        User user = userRepository.findById(userId) // 유저 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음!"));

        Post post = Post.builder() // 게시글 생성
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .date(System.currentTimeMillis())
                .build();

        post.confirmUser(user); // 게시글과 유저 연결

        if (postReqDto.getCategoryNames() != null) { // 카테고리 추가
            for (String categoryName : postReqDto.getCategoryNames()) {
                Category category = Category.builder()
                        .category_name(categoryName)
                        .build();
                category.confirmPost(post);
            }
        }

        Post savedPost = postRepository.save(post); // 게시글 저장
        return PostResDto.from(savedPost); // 게시글 반환
    }

    public PostResDto getPostDto(Long postId) { // 게시글 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
        return PostResDto.from(post); // 게시글 반환
    }

    public List<PostResDto> getAllPosts() { // 모든 게시글 가져오기
        return postRepository.findAll().stream()
                .map(PostResDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResDto updatePost(Long postId, PostReqDto postReqDto) { // 게시글 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));

        post.update(postReqDto.getTitle(), postReqDto.getContent()); // 게시글 업데이트

        // 카테고리 업데이트 (기존 카테고리 삭제 후 재등록)
        post.getCategory().clear(); 
        if (postReqDto.getCategoryNames() != null) { // 카테고리 추가
            for (String categoryName : postReqDto.getCategoryNames()) { // 카테고리 반복
                Category category = Category.builder()
                        .category_name(categoryName)
                        .build();
                category.confirmPost(post); // 카테고리와 게시글 연결
            }
        }

        return PostResDto.from(post);
    }

    @Transactional
    public void deletePost(Long postId) { // 게시글 찾기
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음!"));
        postRepository.delete(post); // 게시글 삭제
    }
}
