package me.kzv.websocketstomp.listener

import me.kzv.websocketstomp.exception.StompExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.*
import java.util.concurrent.ConcurrentHashMap


@Component
class StompEventListener {
    private val logger = LoggerFactory.getLogger(StompExceptionHandler::class.java)

    private val sessions = ConcurrentHashMap<String, String>()

    fun getSessions() = sessions.values.toSet()

    @EventListener
    fun listener(sessionConnectEvent: SessionConnectEvent?) {
        logger.info("sessionConnectEvent. {}", sessionConnectEvent)
    }

    @EventListener
    fun listener(sessionConnectedEvent: SessionConnectedEvent) {
        logger.info("sessionConnectedEvent. {}", sessionConnectedEvent)
        val sessionId = sessionConnectedEvent.message.headers["simpSessionId"].toString()
        sessions[sessionId] = sessionId
    }

    @EventListener
    fun listener(sessionSubscribeEvent: SessionSubscribeEvent?) {
        logger.info("sessionSubscribeEvent. {}", sessionSubscribeEvent)
    }

    @EventListener
    fun listener(sessionUnsubscribeEvent: SessionUnsubscribeEvent?) {
        logger.info("sessionUnsubscribeEvent. {}", sessionUnsubscribeEvent)
    }

    @EventListener
    fun listener(sessionDisconnectEvent: SessionDisconnectEvent) {
        logger.info("sessionDisconnectEvent. {}", sessionDisconnectEvent)
        val sessionId = sessionDisconnectEvent.message.headers["simpSessionId"].toString()
        sessions.remove(sessionId)
    }
}