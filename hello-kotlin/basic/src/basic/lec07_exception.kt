package basic

fun main() {

}

fun parseIntOrThrow(str: String):Int {
    try {
        return str.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("주어진 문자열은 ${str}는 숫자가 아닙니다")
    }
}

fun parseIntOrThrow2(str: String):Int? { // try ~ catch 도 하나의 Expression 으로 간주되어 return 하나로 묶을 수 있다.
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
