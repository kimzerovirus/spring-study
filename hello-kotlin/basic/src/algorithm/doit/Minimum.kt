package algorithm.doit


fun main() {
    minimum(1,2,3)
}

// a, b 를 비교 후 그 중 작은 값을 c 와 비교
fun minimum(a: Int, b: Int, c: Int) {
    var answer = if (a < b) a else b
    if (c < answer) answer = c

    println(answer)
}