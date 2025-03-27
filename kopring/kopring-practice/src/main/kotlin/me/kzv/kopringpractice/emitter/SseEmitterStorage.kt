package me.kzv.kopringpractice.emitter

import org.springframework.stereotype.Repository
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.ConcurrentHashMap


@Repository
class SseEmitterStorage {
    private val emitters: MutableMap<Long, SseEmitter> = ConcurrentHashMap()

    fun save(id: Long, emitter: SseEmitter) {
        emitters[id] = emitter
    }

    fun deleteById(id: Long) {
        emitters.remove(id)
    }

    fun get(id: Long): SseEmitter? {
        return emitters[id]
    }
}