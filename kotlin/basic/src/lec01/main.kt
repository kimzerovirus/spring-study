package lec01

fun main() {
    // 코틀린은 타입을 무조건 지정해주지 않아도 됨
    // 명시적으로 작성한다면 타입스크립트처럼 작성
    // var == let
    var number1:Long = 10L
    number1 = 5L
    println(number1)

    // val == const
    val number2 = 10L
    //  number2 = 5L [X]

    // long - Primitive Type
    // Long - Reference Type <- 연산과정에서 boxing과 unboxing이 일어나 성능 손해 볼 수 있음
    // 코틀린은 구별하지 않고 Long으로 작성하지만 알아서 Long과 long으로 변환해준다.

    // 코틀린은 기본적으로 변수에 null을 할당할 수 없게 설계되어 있으며,
    // nullable로 설정하려면 타입을 명시하고 ?를 더해준다.
    var number3: Long? = 1_000L
    number3 = null

    // 코틀린에서 객체 인스턴스화를 할때에는 new를 붙이지 않는다.
    var person = Person("sudo")
}

class Person constructor(name: String){
    /*
        코틀린은 하나의 주(primary)생성자와 다수의 부(secondary)생성자가 있다.
        주 생성자는 클래스의 헤더로써 클래스의 이름과 동일한 이름을 사용한다.
        만약, 주 생성자에 어노테이션이나 가시성 변경자가 없다면 생성자 키워드는 생략할 수 있다.
        class Person constructor(name: String) -> class Person(name: String)
    */
}
