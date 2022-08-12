package com.kzv.tdd.springbootjpa_tdd.web.dto;

import com.kzv.tdd.springbootjpa_tdd.web.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    //Entity에서 값을 받아 필드로 넣어준다.
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
