package basic

import java.lang.IllegalArgumentException

// 자바로 만든 라이브러리에 코틀린 코드를 덧붙이자!
// 함수의 코드 자체는 클래스 밖에 있는데 마치 클래스 안에 있는 멤버 함수처럼 사용할 수 있게 하는게 확장함수

fun main(){
    val str = "ABC"
    println(str.lastChar()) // 멤버 함수처럼 사용

    3.add(4)
    3.add2(4)
    3 add2 4 // infix 함수
    3.add3(4)
}

/**
 * String 클래스를 확장하는 확장함수
 * fun 확장하려는 클래스.함수이름(파라미터): 리턴타입{
 *      this 를 이용해 실제 클래스 안의 값에 접근
 * }
 *
 * 확장함수는 클래스에 있는 private 또는 protected 멤버를 가져올 수 없다.
 * 확장함수와 멤버함수의 시그니쳐가 겹치면 멤버함수가 우선 호출된다.
 */
fun String.lastChar(): Char{
    return this[this.length - 1] // 문자열 가져올 때 [] 대괄호 사용
}

/**
 * infix 함수
 */
fun Int.add(other: Int): Int {
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}

/**
 * inline 함수
 *
 * 함수를 파라미터로 전달할 떄에 오버헤드를 줄일 수 있다.
 * 하지만 inline 함수의 사용은 성능 측정과 함께 신중히 사용되어야 한다.
 */
inline fun Int.add3(other: Int): Int {
    return this + other
}

/**
 * 지역함수
 *
 * 코드의 depth 가 길어지므로 코드 지저분해짐...
 */
fun createPerson(firstName:String): Person{
    fun validateName(name: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("${name}은 비어있을 수 없다.")
        }
    }
    validateName(firstName)

    return Person(firstName, 1)
}