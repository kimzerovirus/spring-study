package me.kzv.chapter04.money;

import java.math.BigDecimal;

/**
 * chapter1 에서는 금액을 Long 으로 구현하였지만
 * 이번 챕터에서는 Money 타입으로 구현한다.
 */

public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    private static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(
                BigDecimal.valueOf(percent)
        ));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

}
