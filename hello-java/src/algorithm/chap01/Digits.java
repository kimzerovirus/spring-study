package algorithm.chap01;

import java.util.Scanner;

/**
 * @author kimzerovirus
 * @project hello-java
 * @date 2021-11-12
 */


//드모르간의 법칙 !(n >=10 && <= 99)
public class Digits {
    public static void main(String[] args){
        Scanner stdIn = new Scanner(System.in);
        int n;

        System.out.println("2자리 정수");

        do {
            System.out.println("입력: ");
            n = stdIn.nextInt();
        } while (n < 10 || n > 99);

        System.out.println("변수 n의 값은"+n+"가(이) 되었습니다.");
    }
}
