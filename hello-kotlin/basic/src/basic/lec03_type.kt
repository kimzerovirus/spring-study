package basic

import java.lang.IllegalArgumentException

fun main() {
    val number1: Int? = 3
    val number2: Long = number1?.toLong() ?: 0L // to타입()함수로 타입을 맞춰줘야 된다.
    val result = number1 ?: 0 / number2.toDouble()

    printAgePossibleNullPerson(null)
    val person = printAgePossibleNullPerson(Person("HI", 18))
    val name = "asdf"
//    Java ST
//    System.out.println(String.format("이름: %s", person.name));

//    if (person is Person) {
//        println("이름:${person.name}")
//    }

//    println("이름 : $name")

    // java는 stringbuider로 append 하던것을 이렇게 작성가능
    val str = """
        ABC
        hello
        world
        ${name}
    """.trimIndent()

    // Java ST
    // String str = "asdf";
    // char ch = str.charAt(1);
    println(str[0]) // 요건 자바스크립트랑 비슷한듯a
    println(str[2])
}

fun printAgeIfPerson(obj: Any) {
    if (obj is Person) {
        val person = obj as Person //생략 가능 (스마트 캐스트 if에서 타입을 걸렀으므로 알아서 형변환해준것)
        println(person.age)
    }
}

fun printAgePossibleNullPerson(obj: Any?) {
    val person:Person? = obj as? Person
    println(person?.age)
}

fun fail(msg: String): Nothing {
    throw IllegalArgumentException(msg)
}

/*
    * 코틀린 만의 특이한 타입 *
    - Java의 Object = Any
    - Java의 void = Unit (다른점은 Unit 그 자체로 타입인자로 사용이 가능하다는 점이다.) 또한 함수형 프로그래밍에서 Unit은 단 하나의 인스턴스만을 갖는 타입을 의미하므로 void처럼 없는 존재가 아니라 실재하는 타입이라는 것이다.
    - 물론 코틀린도 void를 제공한다. Void
    - Nothing 타입은 함수가 무한루프의 경우나 예외를 던지는 경우와 같이 정상적으로 끝나지 않는 경우 사용한다.
    - String indexing
 */
