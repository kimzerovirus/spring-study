package me.kzv.domain;

import lombok.Data;

import java.util.Date; //sql패키지는 java.util.Date을 상속받는다

@Data
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;

}
