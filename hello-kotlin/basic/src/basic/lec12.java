package basic;

public class lec12 {
    public static void main(String[] args) {
        moveSomething(new Movable(){
            @Override
            public void move(){
                System.out.println("움직인다");
            }

            @Override
            public void fly(){
                System.out.println("난다");
            }
        });
    }

    static class JavaPerson {
        private static final int MIN_AGE = 1;

        public static JavaPerson newPerson(String name){
            return new JavaPerson(name, MIN_AGE);
        }

        private String name;
        private int age;

        public JavaPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class JavaSingleton{
        private static final JavaSingleton INSTANCE = new JavaSingleton();

        private JavaSingleton(){}

        public static JavaSingleton getInstance() {
            return INSTANCE;
        }
    }

    private static void moveSomething(Movable movable) {
        movable.move();
        movable.fly();
    }

    interface Movable{
        void move();
        void fly();
    }
}
