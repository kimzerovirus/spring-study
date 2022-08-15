package basic;

public class lec09 {
    private final String name;
    private int age;

    public lec09(String name, int age) {
        if (this.age <= 0) {
            throw new IllegalArgumentException("나이는 1살 이상이어야 됩니다");
        }
        this.name = name;
        this.age = age;
    }

    public lec09(String name) {
        this(name, 1);
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public boolean isAdult(){
        return this.age >= 20;
    }
}

