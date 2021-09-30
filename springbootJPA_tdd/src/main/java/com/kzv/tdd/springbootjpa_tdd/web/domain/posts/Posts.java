package com.kzv.tdd.springbootjpa_tdd.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}

/*
    @Entity
     - DB의 테이블과 링크될 클래스임을 선언
     - 기본값으로 클래스(자바)의 카멜케이스 = 데이터베이스의 언더스코어 네이밍(언더바)과 매칭된다.

    @Id
     - Primary Key, 즉 PK임을 의미함

    @GeneratedValue
     - PK규칙을 나타낸다
     - 스프링부트 2.0의 경우 GenerationType.IDENTITY옵션을 추가해야 auto_increment사용가능

    @Column
      - 테이블의 칼럼으로 선언하지 않아도 해당 클래스의 모든 필드는 칼럼이 된다.
      - 기본값 등 추가설정을 할 경우 선언해야된다.

    @NoArgsConstructor
     - 기본 생성자 자동추가

    @Builder
     - 해당 클래스의 빌더 패턴 클래스 생성
     - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
 */
