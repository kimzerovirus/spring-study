package java.extend;

/**
 * @author kimzerovirus
 * @project hello-java
 * @date 2021-11-12
 */


class Man {
    String name;

    public void tellYourName() {
        System.out.println("My name is " + name);
    }
}

class BusinessMan extends Man {
    String company;
    String position;

    public BusinessMan(String name, String company, String position) {
        this.name = name;
        this.company = company;
        this.position = position;
    }

    public void tellYourInfo() {
        System.out.println("My company is " + company);
        System.out.println("My position is " + position);
        tellYourName();
    }
}

public class MyBusinessMan {
    public static void main(String[] args) {
        BusinessMan man = new BusinessMan("KKK", "YR", "Developer");
        man.tellYourInfo();
    }
}
