package me.kzv.kopringpractice.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class StompController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    fun basic() {

    }
}