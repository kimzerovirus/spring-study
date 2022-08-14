package datastructure


fun main() {
    val board = Array(3) { CharArray(3) }

    // indices : 일반적으로 0 < content.length 표현과 같음
//    for (i in board.indices) { // indices -> public val ByteArray.indices: IntRange get() = IntRange(0, lastInde
//        println(board[i])
//    }

    for (i in 0..2) { // in 은 양 끝이 포함관계이다.  일반적인 for 문은 0 부터 시작해서 끝 값 미만으로 표현하는것과는 다르게...
        for (j in 0..2) {
            board[i][j] = '-'
        }
    }

    // 2차원 배열을 바로 초기화해서 선언하려면 arrayOf
    val boardTwo = arrayOf(
        arrayOf('0','-','-'),
        arrayOf('0','-','-'),
        arrayOf('0','-','-')
    )

    board[0][0] = '0'
    board[1][0] = '0'
    board[2][0] = '0'

    println(board.contentDeepToString()) // <in java> Arrays.deepToString(board)
    println(boardTwo.contentDeepToString())
}
