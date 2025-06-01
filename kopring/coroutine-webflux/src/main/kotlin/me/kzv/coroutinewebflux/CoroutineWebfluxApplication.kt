package me.kzv.coroutinewebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutineWebfluxApplication

fun main(args: Array<String>) {
    runApplication<CoroutineWebfluxApplication>(*args)
}
