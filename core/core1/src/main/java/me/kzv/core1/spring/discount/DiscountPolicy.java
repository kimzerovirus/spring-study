package me.kzv.core1.spring.discount;

import me.kzv.core1.spring.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
