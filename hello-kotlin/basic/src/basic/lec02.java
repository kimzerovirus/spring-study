package basic;

public class lec02 {

    public static void main(String[] args) {

    }

    public boolean startsWithA1(String str) { //null이 들어올 수 있음
        if (str == null) {
            throw new IllegalArgumentException("null이 들어왔습니다.");
        }
        // boolean -> null이 들어갈 수 없음
        return str.startsWith("A");
    }
    public Boolean startsWithA2(String str) {
        if (str == null) {
            return null;
        }
        // Boolean -> null이 들어갈 수 있음
        return str.startsWith("A");
    }
    public boolean startsWithA3(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("A");
    }

    public long calculate(Long number) {
        if (number == null) {
            return 0;
        }

        // next
        return number;
    }
}
