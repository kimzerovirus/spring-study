package me.kzv.core1.old;

import me.kzv.core1.old.discount.DiscountPolicy;
import me.kzv.core1.old.discount.RateDiscountPolicy;
import me.kzv.core1.old.member.MemberService;
import me.kzv.core1.old.member.MemberServiceImpl;
import me.kzv.core1.old.member.MemoryMemberRepository;
import me.kzv.core1.old.order.OrderService;
import me.kzv.core1.old.order.OrderServiceImpl;

/**
 * 의존 관계 주입
 */
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
