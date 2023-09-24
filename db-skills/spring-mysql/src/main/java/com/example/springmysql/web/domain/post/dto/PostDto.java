package com.example.springmysql.web.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {
    private Long id;
    private Long memberId;
    private String content;
    private LocalDateTime createdAt;
    private Long likeCount;
}
