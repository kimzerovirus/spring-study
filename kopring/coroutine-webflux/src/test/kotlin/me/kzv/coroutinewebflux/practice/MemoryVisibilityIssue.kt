package me.kzv.coroutinewebflux.practice

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

//@Volatile
var count = 0
// 일반적인 변수로 count를 멀티스레드 환경에서 공유한다면 공유 상태로 인한 메모리 가시성 문제 발생!!!
// Volatile은 CPU Cache 메모리에 접근하는 것이 아니라 Main 메모리에 접근한다.
// 하지만 Volatile은 동시 접근에 따른 연산에 대해 보장하지 못한다.
// 만약 두 연산이 동시에 접근해 1이라는 값을 받아 1을 더한다면 3이 아니라 2라는 결과가 나올 수 있다.
// 동시 접근에 대한 제한을 가장 간단한 방법은 공유 변수의 변경 가능 지점을 임계 영역으로 만들어 동시 접근을 제한하는 것이다.
// 코틀린 코루티에서는 임계 영역을 만들기 위한 Mutex 객체를 제공한다.

val mutex = Mutex()
// lock, unlock 함수 제공
// 락 획득 이후에는 반드시 락을 해제 해야한다.
// 만일 해제하지 않는다면, 해당 임게영역은 다른 스레드에서 접근이 불가능하게 되어 문제가 될 수 있다.

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        repeat(10_000) {
            launch {
//                mutexFunc()
                coroutineScopeFunc()
            }
        }
    }
    println("count=$count")
}

private suspend fun mutexFunc() {
    mutex.withLock {
        // mutex.lock()
        // count += 1
        // mutex.unlock()
        count += 1
        // 코루틴이 Mutext 객체의 lock 함수를 호출했을 때 이미 다른 코루틴이 lock을 호출한 상황이라면
        // 코루틴은 기존의 락이 해제될 때까지 스레드를 양보하고 일시 중단한다.
        // 즉 lock함수는 일시 중단 함수이다. >>> 해당 작업은 일시 중단되어 스레드가 블로킹되지 않고 스레드에서 다른 작업을 실행할 수 있게 한다.
    }
}

// 공유 상태에 접근할 때 하나의 전용 스레드에 접근하도록 강제하여 공유 상태 변경 문제를 해결할 수도 있다.
// 아래는 단일 스레드로 구성된 coroutine dispatcher이다.
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
val countChangeDispatcher = newSingleThreadContext("CountChangeThread")

private suspend fun coroutineScopeFunc() = coroutineScope {
    withContext(countChangeDispatcher) {
        count += 1
    }
}

// 덧. Atomic 객체를 이용하는 방법도 있긴함
// 객체 참조시에는 AtomicReference(DataClass()) 와 같이 원자성 객체를 만들 수 있음
// 하지만 코루틴이 원자성 있는 객체에 접근할 때 해당 객체에 대한 연산을 실행 중이면 코루틴은 스레드를 블로킹하고 연산이 끝날 때까지 기다린다.
// 따라서 원자성 있는 객체를 코루틴에서 다룰 때 객체가 스레드를 블로킹 할 수 있음을 주의해야함