package me.kzv.chapter02.movie.step02.pricing;

import me.kzv.chapter02.movie.step02.DiscountCondition;
import me.kzv.chapter02.movie.step02.Screening;

/**
 * 할인 여부를 판단하기 위해 사용할 순번
 */
public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return false;
    }

}
