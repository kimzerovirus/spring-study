package algorithm.doit

fun main() {
    for (i in 1..9) {
        for (j in 1..9) {
            val num = if (i * j < 10) " " + i * j else i * j
            print("$num ")
        }
        println()
    }
}