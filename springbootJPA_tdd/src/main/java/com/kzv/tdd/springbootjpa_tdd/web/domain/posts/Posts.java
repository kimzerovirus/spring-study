package com.kzv.tdd.springbootjpa_tdd.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // <-없으면 not a managed type으로 레포지토리에서 에러가 난다.
public class Posts {

    // 엔티티 클래스로 Setter 메소드를 만들지 않는다.
    // 대신 필드값 변경이 필요할 경우 해당 필드에 대한 목적과 의도가 확실하게 나타나는 메소드를 추가해준다.
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

    // 기본적으로 생성자를 통해 최종값을 채우지만 여기서는 @Builder를 통해 채우는 방식으로 작성한다. (이렇게 하면 생성자와 달리 내가 무엇을 채우려는지 명확한 목적을 알기 쉽다)
    /*
        예시) - 생성자
        public Example(String a, String b){
            this.a = a;
            this.b = b;
        }
        => new Example(b,a)와 같이 a,b의 순서를 변경하면 문제점을 찾기 힘들다

        Example.builder()
            .a(a)
            .b(b)
            .build();
        => 명확하게 a에 a를 b에 b를 넣어준다는걸 알 수 있다.
     */

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
