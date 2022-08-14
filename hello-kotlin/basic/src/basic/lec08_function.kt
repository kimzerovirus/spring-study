package basic

fun main() {
    repeat("Hello World", useNewLine = false) // Named Argument

    printAll("A", "B", "C")
    // 배열을 넣어줄 경우 - 자바의 경우 바로 변수로 넣어줘도 됐지만
    val array = arrayOf("A", "B", "C")
    printAll(*array) // spread 연산자 *
}

/*
    fun max(a: Int, b: Int): Int {
        return if (a > b) {
            a
        }else{
            b
        }
    }
 */
fun max(a: Int, b: Int) = if (a > b) a else b // = 을 사용할 경우 반환 타입 생략 가능 하지만 block {} 을 사용하는 경우에는 명시해줘야함.

/**
 *  default parameter
 *  물론 코틀린에서도 오버로딩은 가능하다.
 */
fun repeat(
    str: String,
    num: Int = 3,
    useNewLine: Boolean = true
) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }

}

/**
 * 가변인자
 * ... 대신 vararg
 */
fun printAll(vararg strings: String) {
    for (str in strings) {
        println(str)
    }
}