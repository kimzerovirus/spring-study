package me.kzv.websocketstomp.exception

import org.slf4j.LoggerFactory
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.ControllerAdvice
import java.io.IOException


@ControllerAdvice
class StompExceptionHandler (
    private val messagingTemplate: SimpMessagingTemplate
){
    private val logger = LoggerFactory.getLogger(this::class.java)

    @MessageExceptionHandler
    fun handleException(exception: Exception) {
        logger.error("exception: {}", exception.javaClass)
    }

    @MessageExceptionHandler
    fun handleException(exception: RuntimeException) {
        logger.error("exception: {}", exception.javaClass)
    }

    @MessageExceptionHandler
    @SendTo("/topic/hello")
    fun handleException(exception: IOException, headers: MessageHeaders?): String {
        logger.error("exception: {}", exception.javaClass)
        return "메시지 전송에 실패하였습니다."
    }

}