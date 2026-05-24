package com.likelion.likelion_7th.dto.Req;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostReqDto {
    private String title;
    private String content;
    private List<String> categoryNames;
}
