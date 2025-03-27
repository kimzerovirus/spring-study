package me.kzv.kopringpractice.controller

import me.kzv.kopringpractice.service.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RequestMapping("/notifications")
@RestController
class NotificationController(
    private val notificationService: NotificationService
) {
    private val logger = LoggerFactory.getLogger(NotificationController::class.java)

    @GetMapping(value = ["/subscribe/{id}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun subscribe(@PathVariable("id") id: Long): SseEmitter {
        logger.info("subscribe: $id")
        return notificationService.subscribe(id)
    }

    @PostMapping("/send-data/{id}")
    fun sendData(@PathVariable("id") id: Long, @RequestBody req: Map<String, Any>) {
        logger.info("Sending - id: $id, data: ${req["data"]}")
        notificationService.notify(id, req["data"] ?: "전송 값 없음")
    }
}