package me.kzv.websocketstomp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class WebsocketStompApplication

fun main(args: Array<String>) {
    runApplication<WebsocketStompApplication>(*args)
}
