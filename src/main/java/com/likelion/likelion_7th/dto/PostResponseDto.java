package com.likelion.likelion_7th.dto;

import com.likelion.likelion_7th.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long id;
    private final String postTitle;
    private final String postContent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long projectUserId;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.projectUserId = post.getProjectUser().getId();
    }
}
