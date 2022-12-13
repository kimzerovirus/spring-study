package me.kzv.chapter02.movie.step02.pricing;

import me.kzv.chapter02.movie.step02.DiscountPolicy;
import me.kzv.chapter02.movie.step02.Money;
import me.kzv.chapter02.movie.step02.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {
//    @Override
//    protected Money getDiscountAmount(Screening screening) {
//        return Money.ZERO;
//    }

    /**
     * getDiscountAmount 로 받아서 할인을 얼마나 하는지 정하는 듯한 애매모호한 메서드명을
     * DiscountPolicy 와 DefaultDiscountPolicy 로 분리하여
     * calculateDiscountAmount 메서드로 값을 0을 반환하도록 함
     */

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
