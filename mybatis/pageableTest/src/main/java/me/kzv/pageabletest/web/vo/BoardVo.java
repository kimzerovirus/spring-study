package me.kzv.pageabletest.web.vo;

import lombok.Data;

@Data
public class BoardVo {
    private int idx;
    private String name;
    private String content;
//    private java.sql.Date regDate;
    private String regDate;
}
