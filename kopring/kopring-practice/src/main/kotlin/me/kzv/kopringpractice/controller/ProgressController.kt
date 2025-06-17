package me.kzv.kopringpractice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter
import java.util.concurrent.Executors


@RestController
class ProgressController {
    @GetMapping("/api/progress")
    fun streamProgress(): ResponseBodyEmitter {
        val emitter = ResponseBodyEmitter()

        Executors.newSingleThreadExecutor().submit {
            try {
                var i = 1
                while (i <= 100) {
                    emitter.send(i)
                    Thread.sleep(500)
                    i += (1..5).random()
                }
                emitter.send(100)
                emitter.complete()
            } catch (e: Exception) {
                emitter.completeWithError(e)
            }
        }

        return emitter
    }
}