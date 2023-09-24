package com.example.springmysql.web.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class  MemberDto {
    private Long id;
    private String nickname;
    private String email;
    private LocalDate birthday;
}