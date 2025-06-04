package me.kzv.coroutinewebflux.practice

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

fun main() = runBlocking {
    var count = 0
    var childCount = 0
    launch {
        while (childCount <= 100) {
            println("자식 코루틴에서 작업 실행 중 - ${childCount++}")
            yield() // 스레드 사용 권한 양보
        }
    }

    while (count <= 100) {
        println("부모 코루틴에서 작업 실행 중 - ${count++}")
        yield() // 스레드 사용 권한 양보
    }
}