package basic;

public class lec10 {

    public static void main(String[] args) {

    }

    abstract class JavaAnimal {
        protected final String species;
        protected final int legCount;

        public JavaAnimal(String species, int legCount) {
            this.species = species;
            this.legCount = legCount;
        }

        abstract public void move();

        public String getSpecies() {
            return species;
        }

        public int getLegCount() {
            return legCount;
        }
    }

    class JavaCat extends JavaAnimal {

        public JavaCat(String species) {
            super(species, 4);
        }

        @Override
        public void move() {
            System.out.println("사뿐사뿐 걸어가~");
        }
    }

    final class JavaPenguin extends JavaAnimal implements JavaFlyable, JavaSwimable {
        private final int wingCount;

        public JavaPenguin(String species) {
            super(species, 2);
            this.wingCount = 2;
        }

        @Override
        public void move() {
            System.out.println("펭귄이 뒤뚱뒤뚱~");
        }

        @Override
        public int getLegCount() {
            return super.getLegCount() + this.wingCount;
        }

        @Override
        public void act() {
            JavaFlyable.super.act();
            JavaSwimable.super.act();
        }
    }

    interface JavaSwimable {
        default void act() {
            System.out.println("어푸 어푸~");
        }
    }

    interface JavaFlyable {
        default void act() {
            System.out.println("파닥 파닥~");
        }
    }
}
