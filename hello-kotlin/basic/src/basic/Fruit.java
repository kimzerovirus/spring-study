package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fruit {
    private final String name;
    private final int price;

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}

interface FruitFilter {
    boolean isSelected(Fruit fruit);
}

