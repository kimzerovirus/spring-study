package algorithm.doit

fun main() {
    println(seqSearchWhile(listOf(1, 2, 3, 4, 5), 3))
}

fun seqSearchWhile(a: List<Int>, key: Int): Int {
    var i = 0
    var n = a.size
    while (true) {
        if (i == n) return -1 // 검색 실패
        if (a[i] == key) return i
        i++
    }
}

fun seqSearchFor1(a: List<Int>, key: Int): Int {
    for (i: Int in 0..a.size) {
        if(a[i] == key) return i
    }
    return -1
}

fun seqSearchFor2(a: List<Int>, key: Int): Int {
    // for 문 안의 리턴 만으로는 안됨 default return 이 있어야 됨
    var result: Int = -1;
    for ((i, item) in a.withIndex()) {
//        result = if(item == key) i else -1
        if(item == key) result = i
    }

    return result
}