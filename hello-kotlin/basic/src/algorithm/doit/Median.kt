package algorithm.doit

/** 중위값 구하기 */
fun median(a: Int, b: Int, c: Int): Int {
    return if (a >= b) { // a 는 b 보다 크거나 같다
        if (b >= c) { // a >= b >= c
            b
        } else if (a <= c) { // c >= a >= b
            a
        } else { // a >= c >= b
            c
        }
    // a 가 b 보다 작은 상황에서 c 의 값?
    } else if (a > c) { // b > a > c
        a
    } else if (b > c) { // a 가 b 보다 작고 c 보다도 작은 경우 b > c > a
        c
    } else { // a 가 b 보다 작고 c 는 a 와 b 보다 크다 c > b > a
        b
    }
}

fun main() {
    val answer = median(2, 2, 1)
    println(answer)
}