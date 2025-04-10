package me.kzv.websocketstomp.controller

import me.kzv.websocketstomp.dto.ReqDto
import me.kzv.websocketstomp.dto.ResDto
import me.kzv.websocketstomp.dto.ResSessionsDto
import me.kzv.websocketstomp.listener.StompEventListener
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.scheduling.TaskScheduler
import org.springframework.stereotype.Controller
import java.io.IOException
import java.security.InvalidParameterException
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledFuture


@Controller
class StompController (
    private val eventListener: StompEventListener,
    private val messagingTemplate: SimpMessagingTemplate,
    private val taskScheduler: TaskScheduler,
){
    private val logger = LoggerFactory.getLogger(StompController::class.java)
    private val scheduledMap = ConcurrentHashMap<String, ScheduledFuture<*>>()

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    fun basic(request: ReqDto): ResDto {
        logger.info("request: $request")

        return ResDto(request.message.uppercase(Locale.getDefault()), LocalDateTime.now())
    }

    @MessageMapping("/multi")
    @SendTo("/topic/hello", "/topic/hello2")
    fun multi(request: ReqDto): ResDto {
        logger.info("request: $request")

        return ResDto(request.message.uppercase(Locale.getDefault()), LocalDateTime.now())
    }

    @MessageMapping("/hello1")
    @SendTo("/topic/hello")
    fun annotations(message: Message<ReqDto>?, headers: MessageHeaders?, request: ReqDto): ResDto {
        logger.info("message: $message")
        logger.info("headers: $headers")
        logger.info("request: $request")

        return ResDto(request.message.uppercase(Locale.getDefault()), LocalDateTime.now())
    }

    @MessageMapping("/hello/{detail}")
    @SendTo("/topic/hello")
    fun detail(@DestinationVariable("detail") detail: String, request: ReqDto): ResDto {
        logger.info("detail: $detail")
        logger.info("request: $request")

        return ResDto("[" + detail + "]_" + request.message.uppercase(Locale.getDefault()), LocalDateTime.now())
    }

    @MessageMapping("/sessions")
    @SendToUser("/queue/sessions")
    fun sessions(request: ReqDto, headers: MessageHeaders): ResSessionsDto {
        logger.info("request: $request")
        val sessions: Set<String> = eventListener.getSessions()
        val sourceSessionId = headers["simpSessionId"].toString()
        return ResSessionsDto(sessions.size, sessions.stream().toList(), sourceSessionId, LocalDateTime.now())
    }

    @MessageMapping("/code1")
    fun code1(request: ReqDto) {
        logger.info("request: $request")
        val resDto = ResDto(request.message.uppercase(Locale.getDefault()), LocalDateTime.now())

        messagingTemplate.convertAndSend("/topic/hello", resDto)
    }

    @MessageMapping("/code2")
    fun code2(request: ReqDto, headers: MessageHeaders) {
        logger.info("request: $request")
        val sourceSessionId = headers["simpSessionId"].toString()
        val sessions: Set<String> = eventListener.getSessions()

        val resSessionsDto =
            ResSessionsDto(sessions.size, sessions.stream().toList(), sourceSessionId, LocalDateTime.now())

        messagingTemplate.convertAndSendToUser(
            sourceSessionId,
            "/queue/sessions",
            resSessionsDto,
            createHeaders(sourceSessionId)
        )
    }

    private fun createHeaders(sessionId: String?): MessageHeaders {
        val headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE)

        if (sessionId != null) {
            headerAccessor.sessionId = sessionId
        }
        headerAccessor.setLeaveMutable(true)
        return headerAccessor.messageHeaders
    }

    @MessageMapping("/start")
    fun start(request: ReqDto, headers: MessageHeaders) {
        logger.info("request: $request")
        val sourceSessionId = headers["simpSessionId"].toString()

        val scheduledFuture: ScheduledFuture<*> = taskScheduler.scheduleAtFixedRate({
            val random = Random()
            val currentPrice = random.nextInt(100)
            messagingTemplate.convertAndSendToUser(
                sourceSessionId,
                "/queue/trade",
                currentPrice,
                createHeaders(sourceSessionId)
            )
        }, Duration.ofSeconds(3))

        scheduledMap[sourceSessionId] = scheduledFuture
    }

    @MessageMapping("/stop")
    fun stop(request: ReqDto, headers: MessageHeaders) {
        logger.info("request: $request")
        val sourceSessionId = headers["simpSessionId"].toString()

        val scheduledFuture = scheduledMap.remove(sourceSessionId)
        scheduledFuture?.cancel(true) ?: logger.info("Not found session $sourceSessionId")
    }

    @MessageMapping("/exception")
    @SendTo("/topic/hello")
    fun exception(request: ReqDto, headers: MessageHeaders?) {
        logger.info("request: $request")
        val message = request.message
        when (message) {
            "runtime" -> throw RuntimeException()
            "nullPointer" -> throw NullPointerException()
            "io" -> throw IOException()
            "exception" -> throw Exception()
            else -> throw InvalidParameterException()
        }
    }
}