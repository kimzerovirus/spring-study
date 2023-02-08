package me.kzv.chapter02.movie.step02.pricing;

import me.kzv.chapter02.movie.money.Money;
import me.kzv.chapter02.movie.step02.DiscountCondition;
import me.kzv.chapter02.movie.step02.DefaultDiscountPolicy;
import me.kzv.chapter02.movie.step02.Screening;

public class AmountDiscountPolicy extends DefaultDiscountPolicy {
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
