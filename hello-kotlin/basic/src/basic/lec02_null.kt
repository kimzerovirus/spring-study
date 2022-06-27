package basic

import java.lang.IllegalArgumentException

fun main() {
    /* Safe Call */
    val str: String? = "ABC"
    // str.length 불가능
    val num = str?.length //null이 아니면 실행하고, null이면 실행하지 않는다. 따라서 num은 str.length가 null이면 null이 된다.

    /* Elvis */
    val num2 = str?.length ?: 0 // 앞의 연산 결과가 null이면 뒤의 값을 사용
}

fun startsWithA1(str: String?): Boolean { //null이 들어올 수도 있으므로 String? // boolean -> null이 들어갈 수 없음
// Java ST
/*
    if (str == null) {
        throw IllegalAccessException("null이 들어왔습니다")
    }

    return str.startsWith("A")
*/

    // Kotlin ST
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null이 들어왔습니다.")
}

fun startsWithA2(str: String?): Boolean? { // Boolean -> null이 들어갈 수 있음 Boolean?
// Java ST
/*
    if (str == null) {
        return null
    }
    return str.startsWith("A")
 */

    // Kotlin ST
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
// Java ST
/*

    if (str == null) {
        return false
    }
    return str.startsWith("A")
 */

    // Kotlin ST
    return str?.startsWith("A") ?: false
}


fun calculate(number: Long?): Long {
    // Early Return
    number ?: return 0 // <- if문으로 유효성 체크하지 않고 이렇게 처리

    // next
    return number
}

// nullable 상태지만(null이 필드에 들어올 수도 있지만) null이 절대 될 수 없다고 생각하는 경우 !!을 사용
fun mustNotNull(str: String?): Boolean {
    return str!!.startsWith("A") // 만약 null이 들어온다면 컴파일에서는 에러 안나지만 런타임에 에러가 난다.
}




