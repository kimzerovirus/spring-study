package basic;

public class lec04_JavaMoney {

    public static void main(String[] args) {
        JavaMoney money1 = new JavaMoney(2_000L);
        JavaMoney money2 = new JavaMoney(2_000L);
        JavaMoney money3 = money1;

        if (money1.compareTo(money2) > 0) {
            System.out.println("Money1이 Money2보다 금액이 큽니다.");
        }

        System.out.println(money1 == money3); // true
        System.out.println(money1.equals(money3)); // true
        System.out.println(money1 == money2); // false
        System.out.println(money1.equals(money2)); // false
    }
}
