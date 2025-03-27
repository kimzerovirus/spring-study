package me.kzv.kopringpractice.controller

import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class SampleController {
    val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/sample/re")
    fun redirectSample(response: HttpServletResponse) {
        logger.info("redirectSample")
        response.addHeader("X-TOKEN", "asdfjlkaskld012")
        response.sendRedirect("/sample")
    }

    @GetMapping("/sample")
    fun sampleSample(@RequestHeader header: HttpHeaders, model: Model): String {
        logger.info("sample ${header["X-TOKEN"]}")
        model.addAttribute("token", header["X-TOKEN"])
        return "sample"
    }

    @GetMapping("/")
    fun index(model: Model): String {
        return "index"
    }

    @PostMapping("/sample")
    fun postSample(@RequestParam("token") token: String, model: Model): String {
        logger.info("rediredted with: $token")
        model.addAttribute("token", token)
        return "sample"
    }
}