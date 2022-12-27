package me.kzv.ecommerce.controller;

import me.kzv.ecommerce.dto.MemberFormDto;
import me.kzv.ecommerce.entity.Member;
import me.kzv.ecommerce.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password) {
        MemberFormDto dto = new MemberFormDto();
        dto.setEmail(email);
        dto.setName("홍길동");
        dto.setAddress("서울시 마포구 합정동");
        dto.setPassword(password);
        Member member = Member.createMember(dto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    public void 로그인_성공_테스트() throws Exception {
        //given
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        //when

        //then
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password(password)
        ).andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void 로그인_실패_테스트() throws Exception {
        //given
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        //when

        //then
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password("12345")
        ).andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}