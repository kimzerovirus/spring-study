package me.kzv.shop.service;

import me.kzv.shop.domain.Member;
import me.kzv.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private EntityManager em;
    
    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);
        
        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }
    
//    @Test(expected = IllegalStateException.class) -> junit4
    @Test
    public void 중복_회원_가입() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        /*
        junit4
//        try {
//            memberService.join(member2);
//        } catch (IllegalStateException e) {
//            return;
//        }

        //then
//        fail("예외가 발생!");
         */

        //then - junit5
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage()); // assertThrows 로 이미 처리 끝

    }

}


/*
     Junit 버전 참고 - https://www.whiteship.me/springboot-no-more-runwith/
 */