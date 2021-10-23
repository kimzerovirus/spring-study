package algorithm.chap01;

import java.util.Scanner;

public class SumWhile {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);

        System.out.println("1부터 n까지의 합");
        System.out.println("n의 값:");
        int n = stdIn.nextInt();

        int sum = 0;
        int i = 1;
        while (i <= n) {
            sum += i;
            i++;
        }
        System.out.println("sum= " + sum);
    }
}
