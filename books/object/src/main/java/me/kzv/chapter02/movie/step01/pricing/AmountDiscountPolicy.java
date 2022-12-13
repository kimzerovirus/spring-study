package me.kzv.chapter02.movie.step01.pricing;

import me.kzv.chapter02.movie.step01.DiscountCondition;
import me.kzv.chapter02.movie.step01.DiscountPolicy;
import me.kzv.chapter02.movie.step01.Money;
import me.kzv.chapter02.movie.step01.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
