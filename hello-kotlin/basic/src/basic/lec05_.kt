package basic

import java.lang.IllegalArgumentException

fun main(){

}

fun validateScoreIsNotNegative(score: Int) { // 함수에서 Unit(void)이 생략됨
    if (score < 0) {
        throw IllegalArgumentException("${score}는 0보다 작을 수 없습니다.")
    }
}

fun getPassOrFail(score: Int): String {
//    return score >= 50 ? "P" : "F"; // 삼항연산자를 지원하지 않는다.

    return if (score >= 50) {
        "P"
    } else {
        "F"
    }
}

/**
 * 삼항 연산자 처럼 작성하기
 */
fun getPassOrFail2(score: Int): String {
    return if (score >= 50) "P" else "F"
}

/**
 * 코틀린의 when 사용하기
 */
fun getPassOrFail3(score: Int): String {
    // 오히려 가독성은 별루인듯 차라리 위에 스타일이 마음에 든다.
    return when {
        score >= 50 -> "P"
        else -> "F"
    }
}

/**
 * 범위 관계
 */
fun validate(score: Int) {
    if (score !in 0..100) {
    }

    if (score in 0..100) {
    }
}

/**
 * 코틀린은 스위치문이 존재하지 않고 대신에 when 을 사용한다.
 */
fun getGradeWithSwitch(score: Int): String {
    return when (score / 10) {
        9 -> "A"
        8 -> "B"
        7 -> "C"
        else -> "D"
    }
}

fun getGradeWithSwitch2(score: Int): String {
    return when (score) {
        in 90..99 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        else -> "D"
    }
}

/**
 * when(값){
 *  조건부 -> 어떠한 구문
 *  // 조건부에는 어떠한 expression 이라도 들어갈 수 있다. (ex. is(=instanceof) Type)
 * }
 */

fun startsWithA(obj: Any): Boolean {
    return when (obj) {
        is String -> obj.startsWith("A")
        else -> false
    }
}

fun judgeNumber(number: Int) {
    when (number) {
        1, 0, -1 -> println("어디서 많이 본듯?")
        else -> println("1, 0, -1이 아닙니다.")
    }
}

fun judgeNumber2(number: Int) {
    when {
        number == 0 -> println("주어진 숫자는 0이다.")
        number % 2 == 0 -> println("주어진 숫자는 짝수이다.")
        else -> println("주어진 숫자는 홀수이다.")
    }
}

/**
 * when 은 Enum Class 혹은 Sealed Class 와 함께 사용할 경우, 더욱더 진가를 발휘한다.
 */
