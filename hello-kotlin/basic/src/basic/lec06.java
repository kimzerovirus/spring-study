package basic;

import java.util.Arrays;
import java.util.List;

public class lec06 {

    public static void main(String[] args) {
        List<Long> numbers = Arrays.asList(1L, 2L, 3L);

        for (long number : numbers) {
            System.out.println(number);
        }
    }
}
