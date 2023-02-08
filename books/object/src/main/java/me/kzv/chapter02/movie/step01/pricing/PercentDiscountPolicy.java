package me.kzv.chapter02.movie.step01.pricing;

import me.kzv.chapter02.movie.step01.DiscountCondition;
import me.kzv.chapter02.movie.step01.DiscountPolicy;
import me.kzv.chapter02.movie.money.Money;
import me.kzv.chapter02.movie.step01.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
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
