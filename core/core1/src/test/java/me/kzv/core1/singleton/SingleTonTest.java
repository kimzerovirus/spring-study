package me.kzv.core1.singleton;

import me.kzv.core1.old.AppConfig;
import me.kzv.core1.old.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingleTonTest {

    @Test
    void 스프링_없는_순수한_DI_컨테이너() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("1 : " + memberService1);
        System.out.println("2 : " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
        // 문제점 : 객체가 무수히 많이 생성되게 된다. -> 메모리 낭비가 심함
        // 해결방안 : 객체를 하나 생성하고 공유하도록 설계한다.
        // 사실 GC 와 컴퓨터 성능이 요즘은 워낙 좋기에 왠만하면 상관없긴 하다.
    }

    @Test
    void 싱글톤_패턴을_적용한_객체(){
        SingleTonService singleTonService1 = SingleTonService.getInstance();
        SingleTonService singleTonService2 = SingleTonService.getInstance();

        System.out.println("1 : " + singleTonService1);
        System.out.println("2 : " + singleTonService2);

        assertThat(singleTonService1).isSameAs(singleTonService2);
    }
    
    @Test
    public void 스프링_컨테이너와_싱글톤() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(me.kzv.core1.spring.AppConfig.class);

        // 1. 조회: 호출할 때 마다 객체를 생성
        me.kzv.core1.spring.member.MemberService memberService1 = ac.getBean("memberService", me.kzv.core1.spring.member.MemberService.class);

        // 2. 조회: 호출할 때 마다 객체를 생성
        me.kzv.core1.spring.member.MemberService memberService2 = ac.getBean("memberService", me.kzv.core1.spring.member.MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("1 : " + memberService1);
        System.out.println("2 : " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
