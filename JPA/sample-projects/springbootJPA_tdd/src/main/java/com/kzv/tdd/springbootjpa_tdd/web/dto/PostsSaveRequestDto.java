package com.kzv.tdd.springbootjpa_tdd.web.dto;

import com.kzv.tdd.springbootjpa_tdd.web.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    // Data Transfer Object 계층 간 데이터 교환 자바빈즈 request와 response 작업을 한다.
    // 반면 Entity는 DB의 테이블과 맞다은 연결점이라고 보면된다.
    // 따라서 Entity클래스의 수정 등의 변경 작업은 되도록 하지 않는다.

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}
