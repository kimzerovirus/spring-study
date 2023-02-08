package me.kzv.chapter02.movie.step01.pricing;

import me.kzv.chapter02.movie.step01.DiscountPolicy;
import me.kzv.chapter02.movie.money.Money;
import me.kzv.chapter02.movie.step01.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }

}
