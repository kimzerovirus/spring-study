package me.kzv.simplesns.exception

import me.kzv.simplesns.controller.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.RuntimeException

@RestControllerAdvice
class GlobalControllerAdvice {

    @ExceptionHandler(SimpleSnsException::class)
    fun applicationHandler(e: SimpleSnsException): ResponseEntity<*> {
        return ResponseEntity.status(e.errorCode.status!!).body(Response.error(e.errorCode.name))
    }

    @ExceptionHandler(RuntimeException::class)
    fun applicationHandler(e: RuntimeException): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.error(ErrorCode.SERVER_ERROR.name))
    }
}