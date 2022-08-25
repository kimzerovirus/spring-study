package me.kzv.core1.spring.discount;

import me.kzv.core1.spring.annotation.MainDiscountPolicy;
import me.kzv.core1.spring.member.Grade;
import me.kzv.core1.spring.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")
//@Primary // Qualifier 로 설정하는 것보다 이렇게 Primary 로 우선순위를 주는 방법이 더 편하다.
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
