package me.kzv.core1.spring;

import me.kzv.core1.spring.discount.DiscountPolicy;
import me.kzv.core1.spring.discount.RateDiscountPolicy;
import me.kzv.core1.spring.member.MemberService;
import me.kzv.core1.spring.member.MemberServiceImpl;
import me.kzv.core1.spring.member.MemoryMemberRepository;
import me.kzv.core1.spring.order.OrderService;
import me.kzv.core1.spring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 의존 관계 주입
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("===================== 1 call memberService=====================");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("===================== 2 call memberRepository=====================");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("===================== 3 call orderService=====================");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
