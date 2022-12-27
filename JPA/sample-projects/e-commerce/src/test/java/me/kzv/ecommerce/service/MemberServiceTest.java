package me.kzv.ecommerce.service;

import me.kzv.ecommerce.dto.MemberFormDto;
import me.kzv.ecommerce.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword("1234");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    public void 회원가입테스트() throws Exception {
        //given
        var member = createMember();

        //when
        var savedMember = memberService.saveMember(member);

        //then
        assertEquals(member.getEmail(), savedMember.getEmail());
    }

    @Test
    public void 중복회원가입테스트() throws Exception {
        //given
        var member1 = createMember();
        var member2 = createMember();

        //when
        memberService.saveMember(member1);
        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        //then
        assertEquals("이미 가입된 회원입니다", e.getMessage());

    }
}