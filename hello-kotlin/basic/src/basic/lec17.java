package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class lec17 {
    public static void main(String[] args) {
        List fruits = List.of(
                new Fruit("사과", 1_000),
                new Fruit("바나나", 2_000)
        );

        filterFruits(fruits, new FruitFilter() {
            @Override
            public boolean isSelected(Fruit fruit) {
                return Arrays.asList("바나나").contains(fruit.getName()) && fruit.getPrice() > 5_000;
            }
        });
    }

    private List<Fruit> findBananas(List<Fruit> fruits) {
        List<Fruit> bananas = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruit.getName().equals("바나나")) {
                bananas.add(fruit);
            }
        }

        return bananas;
    }

    private static List<Fruit> filterFruits(List<Fruit> fruits, FruitFilter fruitFilter) {
        List<Fruit> results = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (fruitFilter.isSelected(fruit)) {
                results.add(fruit);
            }
        }

        return results;
    }

    private static List<Fruit> filterFruits(List<Fruit> fruits, Predicate<Fruit> fruitFilter) {
        return fruits.stream()
                .filter(fruitFilter)
                .collect(Collectors.toList());
    }

}


