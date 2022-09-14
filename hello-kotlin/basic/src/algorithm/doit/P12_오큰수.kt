package algorithm.doit

import java.io.*
import java.util.*

/**
 * 예시 입력
 * 4 - 크기
 * 3 5 2 7
 */

@Throws(IOException::class)
fun main(args: Array<String>) {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = bf.readLine().toInt()
    val A = IntArray(n) // 수열 배열 생성
    val ans = IntArray(n) // 정답 배열 생성
    val str = bf.readLine().split(" ".toRegex()).toTypedArray()
    for (i in 0 until n) {
        A[i] = str[i].toInt()
    }
    val myStack = Stack<Int>()
    myStack.push(0) // 처음에는 항상 스택이 비어있으므로 최초 값을 push하여 초기화
    for (i in 1 until n) {
        //스택 비어있지 않고 현재 수열이 스택 TOP인덱스 가르키는 수열보다 크면
        while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
            ans[myStack.pop()] = A[i] //정답 배열에 오큰수를 현재 수열로 저장하기
        }
        myStack.push(i) //신규데이터 push
    }
    while (!myStack.empty()) {
        // 반복문을 다 돌고 나왔는데 스택이 비어있지 않다면 빌 때 까지
        ans[myStack.pop()] = -1
        // stack에 쌓인 index에 -1을 넣고
    }
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    for (i in 0 until n) {
        bw.write(ans[i].toString() + " ")
        // 출력한다
    }
    bw.write("\n")
    bw.flush()
}

