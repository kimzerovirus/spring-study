package me.kzv.chapter02.movie.step02;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
