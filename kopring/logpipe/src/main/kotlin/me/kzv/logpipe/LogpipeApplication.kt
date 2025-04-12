package me.kzv.logpipe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogpipeApplication

fun main(args: Array<String>) {
    runApplication<LogpipeApplication>(*args)
}
