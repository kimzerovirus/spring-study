package algorithm.chap01;

import java.util.Scanner;

public class SumForPos {
    public static void main(String[] args) {
        Scanner std = new Scanner(System.in);
        int n;

        System.out.println("1~n까지의 합");

        do {
            System.out.println("n의 값: ");
            n = std.nextInt();
        } while (n < 0);

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("1~" + n + "까지의 합 = " + sum);
    }
}
