package com.likelion.likelion_7th.dto.Res;

import com.likelion.likelion_7th.domain.Category;
import com.likelion.likelion_7th.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResDto {
    private Long id;
    private String title;
    private String content;
    private Long date;
    private String username;
    private List<String> categoryNames;
    private int likesCount;
    private List<CommentResDto> comments;

    @Builder
    public PostResDto(Long id, String title, String content, Long date, String username,
                      List<String> categoryNames, int likesCount, List<CommentResDto> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.username = username;
        this.categoryNames = categoryNames;
        this.likesCount = likesCount;
        this.comments = comments;
    }

    public static PostResDto from(Post post) { 
        List<String> categories = post.getCategory() == null ? Collections.emptyList() : // 게시글 카테고리가 없으면 빈 리스트 반환
                post.getCategory().stream()
                        .map(Category::getCategory_name)
                        .collect(Collectors.toList()); // 게시글 카테고리에서 카테고리 이름만 추출하여 리스트로 반환

        List<CommentResDto> commentDtos = post.getComments() == null ? Collections.emptyList() : // 게시글 댓글이 없으면 빈 리스트 반환
                post.getComments().stream()
                        .map(CommentResDto::from)
                        .collect(Collectors.toList()); // 게시글 댓글에서 댓글 디티오로 변환

        return PostResDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .date(post.getDate())
                .username(post.getUser() != null ? post.getUser().getUsername() : null)
                .categoryNames(categories)
                .likesCount(post.getLike() != null ? post.getLike().size() : 0)
                .comments(commentDtos)
                .build();
    }
}
