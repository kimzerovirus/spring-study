package com.example.jwttutorial.security;

import com.example.jwttutorial.dto.MemberRequestDto;
import com.example.jwttutorial.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private AuthService authService;

    @Test
    void tokenTest(){
        MemberRequestDto memberRequestDto = new MemberRequestDto("email", "1234");

        authService.signup(memberRequestDto);
    }
}