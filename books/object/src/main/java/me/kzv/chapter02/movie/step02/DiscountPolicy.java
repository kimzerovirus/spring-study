package me.kzv.chapter02.movie.step02;

import me.kzv.chapter02.movie.money.Money;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
