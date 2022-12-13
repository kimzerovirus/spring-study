package me.kzv.chapter02.movie.step02;


import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy; // 두 가지의 할인 정책이 있다. 1. 금액 할인 2. 비율 할인
        /**
         *  상속과 다형성 - 추상화
         *  할인 정책과 할인 조건
         */
    }

    public Money calculateMovieFee(Screening screening) {
//        if (discountPolicy == null) { // 할인이 없는 경우 바로 기본금액을 반환한다. <- 이렇게 되면 discountPolicy 에서 벗어난 예외가 된다.
//            return fee;
//        }

        /**
         * 코드의 재사용 - 합성
         * 다형성을 위해서는 상속을 그 외에는 합성을 사용하는 편이 좋다.
         */
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }
}
