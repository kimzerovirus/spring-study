package com.example.springmysql.web.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DailyPostCount {
    private Long memberId;
    private LocalDate date;
    private Long postCount;
}
