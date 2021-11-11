package algorithm.chap01;

/**
 * @author kimzerovirus
 * @project hello-java
 * @date 2021-11-12
 */

//곱셈표
public class MultiLoop {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.printf("%3d", i * j);
            }
            System.out.println();
        }
    }
}
