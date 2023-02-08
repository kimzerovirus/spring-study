package me.kzv.chapter02.movie.step01;

import me.kzv.chapter02.movie.money.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  부모 클래스인 DiscountPolicy 에 중복 코드를 넣어 두고
 *  AmountDiscountPolicy 와 PercentDiscountPolicy 를 구현한다.
 *  그리고 DiscountPolicy 객체는 실제로 인스턴스를 생성할 필요가 없으므로 추상 클래스로 만든다.
 *
 *
 *  부모 클래스에 기본적인 알고리즘의 흐름을 구현하고 실제 처리 코드를 자식 클래스에게 위임하는 디자인 패턴을 TEMPLATEMETHOD 패턴이다.
 *
 *  오버라이딩과 오버로딩
 *  - 오버라이딩 : 부모클래스에 정의된 같은 이름, 같은 파라미터 목록을 가진 메서드를 자식 클래스에서 재정의하는 경우
 *  - 오버로딩 : 메서드의 이름은 같지만 제공되는 파라미터의 목록이 다름
 *  ex)
 *          public class Money{
 *             public Money plus(Money amount) {
 *                 return new Money(this.amount.add(amount.amount));
 *             }
 *
 *             public Money plus(long amount) {
 *                 return new Money(this.amount.add(BigDecimal.valueOf(amount)));
 *             }
 *         }
 */

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    // 할인 정책은 여러개의 할인 조건을 받는다.
    public DiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    // 추상 메서드 - 구현을 강제한다.
    protected abstract Money getDiscountAmount(Screening screening);
}
