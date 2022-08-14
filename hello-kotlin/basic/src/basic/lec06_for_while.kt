package basic

fun main() {
    val numbers = listOf(1L, 2L, 3L) // Collection List

    for (number in numbers) {
        println(number)
    }

    for (i in 1..3) { // i++
        println(i)
    }

    for (i in 3 downTo 1) { // i--
        println(i)
    }

    for (i in 1..5 step 2) { // i+=2
        println(i)
    }

// downTo 와 step 은 함수이다.
// (중위 호출 함수) [변수.함수이름(argument)] 대신 띄어쓰기를 사용하여 [변수 함수이름 argument] 와 같이 표현함

    var i = 0
    while (i <= 3) {
        println(i)
        i++
    }
}