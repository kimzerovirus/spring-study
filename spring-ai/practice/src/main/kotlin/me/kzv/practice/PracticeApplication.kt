package me.kzv.practice

import org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PracticeApplication

fun main(args: Array<String>) {
    runApplication<PracticeApplication>(*args)
}
