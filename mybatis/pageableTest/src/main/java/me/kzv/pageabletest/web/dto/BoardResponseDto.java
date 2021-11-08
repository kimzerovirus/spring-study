package me.kzv.pageabletest.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardResponseDto {
    private final int idx;
    private final String name;
    private final String content;
//    private final java.sql.Date regDate;
    private final String regDate;
}
