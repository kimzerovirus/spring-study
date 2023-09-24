package com.example.springmysql.web.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterMemberCommand {
    private String email;
    private String nickname;
    private LocalDate birthday;
}
