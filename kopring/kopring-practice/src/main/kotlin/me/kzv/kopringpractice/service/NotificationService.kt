package me.kzv.kopringpractice.service

import me.kzv.kopringpractice.emitter.SseEmitterStorage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException

@Service
class NotificationService(
    private val sseEmitterStorage: SseEmitterStorage,
) {
    private val logger = LoggerFactory.getLogger(NotificationService::class.java)

    companion object {
        const val DEFAULT_TIMEOUT: Long = 60 * 60 * 1000
    }

    fun notify(id: Long, data: Any) {
        sendEvent(id, data)
    }

    fun subscribe(userId: Long): SseEmitter {
        return createEmitter(userId)
    }

    private fun createEmitter(id: Long): SseEmitter {
        val emitter = SseEmitter(DEFAULT_TIMEOUT)
        sseEmitterStorage.save(id, emitter)
        emitter.onCompletion { sseEmitterStorage.deleteById(id) }
        emitter.onTimeout { sseEmitterStorage.deleteById(id) }
        return emitter
    }

    private fun sendEvent(sendId: Long, data: Any) {
        sseEmitterStorage.get(sendId)?.let {
            try {
                logger.info("Sending - id: $sendId success, data: $data")
                it.send(SseEmitter.event().id(sendId.toString()).name("custom-event-name").data(data)) // event 명으로 화면에서 수신한다.
            } catch (e: IOException) {
                sseEmitterStorage.deleteById(sendId)
            }
        } ?: run {
            logger.warn("sending id : $sendId is not found")
        }
    }
}