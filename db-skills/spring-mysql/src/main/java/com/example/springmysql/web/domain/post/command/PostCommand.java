package com.example.springmysql.web.domain.post.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCommand {
    private Long memberId;
    private String contents;
}
