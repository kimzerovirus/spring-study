package basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lec15 {
    public static void main(String[] args) {
        int[] array = {100, 200};

        for (int i = 0; i < array.length; i++) {
            System.out.println(i);
            System.out.println(array[i]);
        }

        final List<Integer> numbers = Arrays.asList(100, 200);
        numbers.get(0);

        for (int number : numbers) {
            System.out.println(number);
        }

        // JDK8
        Map<Integer, String> oldMap = new HashMap<>();
        oldMap.put(1, "MONDAY");
        oldMap.put(2, "TUESDAY");

        // JDK9
        Map.of(1, "MONDAY", 2, "TUESDAY");
    }
}
