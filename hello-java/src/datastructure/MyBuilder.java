package datastructure;

// buidler pattern 구현
public class MyBuilder {

    private final String name;
    private final int age;

    private MyBuilder(Builder builder) {
        name = builder.name;
        age = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;
        
        public Builder(String name, int age){
            this.name = name;
            this.age = age;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public MyBuilder build(){
            return new MyBuilder(this);
        }
    }
}
