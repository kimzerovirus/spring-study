package algorithm.doit

import java.util.*

fun main() {
    val arr = listOf(1, 2, 3, 4, 5)
    val stack = Stack<Int>()
    var num: Int = 1
    var result: Boolean = true

    for ((index, item) in arr.withIndex()) {
        if (item >= num) {
            while (item >= num) {
                stack.push(num++)
            }
            stack.pop()
        } else {
            val n: Int = stack.pop()
            if (n > item) {
                println("NO")
                result = false
                break
            } else {

            }
        }
        if (result) println("YES")
    }
}