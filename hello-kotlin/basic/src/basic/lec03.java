package basic;


public class lec03 {

    public static void main(String[] args) {

    }

    public static void printAgeIfPerson(Object obj) {
        if (obj instanceof Person) {
            System.out.println(((Person) obj).getAge()); //kotlin에서 가져와지네 ㄷㄷ
        }
    }
}
