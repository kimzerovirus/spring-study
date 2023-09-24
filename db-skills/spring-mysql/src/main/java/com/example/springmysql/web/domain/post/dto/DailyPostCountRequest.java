package com.example.springmysql.web.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class DailyPostCountRequest {
    private Long memberId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate firstDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate localDate;
}
