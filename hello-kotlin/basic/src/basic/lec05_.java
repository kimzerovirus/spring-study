package basic;

public class lec05_ {

    public static void main(String[] args) {

    }

    private void validateScoreIsNotNegative(int score) {
        if (score < 0) {
            throw new IllegalArgumentException(String.format("%s는 0보다 작을 수 없습니다.", score));
        }
    }

    private String getPassOrFail(int score) {
        if (score >= 50) {
            return "P";
        }else{
            return "F";
        }

//         return score >= 50 ? "P" : "F";
    }

    private void validate(int score) {
        if (0 <= score && score <= 100) {
            System.out.println();
        }
    }

    private String getGradeWithSwitch(int score) {
        switch (score / 10) {
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            default:
                return "D";
        }
    }

    private boolean startsWithA(Object obj) {
        if (obj instanceof String) {
            return ((String) obj).startsWith("A");
        } else {
            return false;
        }
    }

    private void judgeNumber(int number) {
        if (number == 1 || number == 0 || number == -1) {
            System.out.println("어디서 많이 본듯?");
        } else {
            System.out.println( "1, 0, -1이 아닙니다.");
        }
    }

    private void judgeNumber2(int number) {
        if (number == 0){
            System.out.println("주어진 숫자는 0이다.");
            return;
        }

        if (number % 2 == 0) {
            System.out.println("주어진 숫자는 짝수이다.");
            return;
        }

        System.out.println("주어진 숫자는 홀수이다.");
    }
}
