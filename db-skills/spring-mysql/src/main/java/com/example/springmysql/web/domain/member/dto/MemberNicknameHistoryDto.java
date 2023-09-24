package com.example.springmysql.web.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberNicknameHistoryDto {
    private Long id;
    private Long memberId;
    private String nickname;
    private LocalDateTime createdAt;
}
