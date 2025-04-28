package me.kzv.architecture;

public class Calculator {
    public double add(double number1, double number2) {
        return number1 + number2;
    }

    public double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("음수의 제곱근을 구할 수 없다");
        }
        return Math.sqrt(x);
    }

    public double divide(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("0으로 나눌 수 없다");
        }
        return x / y;
    }


}
