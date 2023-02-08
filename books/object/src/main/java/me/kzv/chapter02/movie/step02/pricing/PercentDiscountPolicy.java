package me.kzv.chapter02.movie.step02.pricing;

import me.kzv.chapter02.movie.money.Money;
import me.kzv.chapter02.movie.step02.DiscountCondition;
import me.kzv.chapter02.movie.step02.DefaultDiscountPolicy;
import me.kzv.chapter02.movie.step02.Screening;

public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
