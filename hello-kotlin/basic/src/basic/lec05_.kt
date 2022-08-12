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
        return "P";
    } else {
        return "F";
    }
}

