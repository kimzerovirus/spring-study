package algorithm.chap01;

import java.util.Scanner;

/**
 * @author kimzerovirus
 * @project hello-java
 * @date 2021-11-12
 */

//"왼쪽 아래가 직각인 이등변 삼각형"
public class TriangleLB {
    public static void main(String[] args) {
        Scanner std = new Scanner(System.in);
        int n;

        do {
            System.out.println("몇 단 삼각형입니까?");
            n = std.nextInt();
        } while (n <= 0);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
