package algorithm.doit

/**
 * 7개의 자연수가 주어질 때, 이들 중 홀수인 자연수들을 모두 골라 그 합을 구하고, 고른 홀수들 중 최소값을 구하기
 */

fun main() {
    val answer = odd(listOf(1, 2, 3, 4, 5, 6, 7))
    println(answer)
}

fun odd(arr: List<Int>): List<Int> {
    val answer = mutableListOf<Int>()
    var sum = 0
    var min = Int.MAX_VALUE

    for (x in arr) {
        if (x % 2 == 1) {
            sum += x
            if(x < min) min = x
        }
    }

    answer.add(sum)
    answer.add(min)

    return answer
}