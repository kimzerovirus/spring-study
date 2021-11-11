package algorithm.chap02;

/**
 * @author kimzerovirus
 * @project hello-java
 * @date 2021-11-12
 */


public class IntArray {
    public static void main(String[] args) {
        int[] a = new int[5];

        a[1] = 36;
        a[2] = 50;

        a[4] = a[1] * 2;

        for(int i =0; i<a.length; i++){
            System.out.println("a[" + i + "] = " + a[i]);
        }
    }
}
