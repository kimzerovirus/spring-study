# Hello-Java

함수는 독립적이지만 메소드는 클래스에 종속적이다.

## 인스턴스와 힙 메모리
- new 키워드를 통해 생성된 객체(인스턴스)
- 생성된 인스턴스는 힙 메모리(동적 메모리)에 할당된다.
- 자바에서는 GC가 주기적으로 사용하지 않는 메모리를 수거한다.

## 생성자
- 클래스에는 반드시 적어도 하나의 생성자가 존재하는데 자바에서는 구현하지 않으면 default constructor(매개변수가 없는 NoArgsConstructor)가 컴파일 시점에 자동으로 생성된다.
- 생성자는 클래스명으로 만들면된다.

## this
- 인스턴스 자신의 메모리를 가리킨다.
- 생성자에서 또 다른 생성자를 호출 할때 사용한다.
- 자신의 주소(참조값)을 반환한다.
```
public class Person{
    String name;
    int age;
    
    public Person(){}
    
    public Person(String name, int age){
            this.name = name; // Person클래스의 name속성에 받아온 name을 집어 넣는다.
            this.age = age;
    }
    
}
```