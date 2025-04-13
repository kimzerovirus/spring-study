package me.kzv.logpipe.shorten

import me.kzv.logpipe.shorten.exception.LackOfShortenUrlKeyException
import me.kzv.logpipe.shorten.exception.NotFoundShortenUrlException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(assignableTypes = [ShortenUrlApi::class])
class ShortenUrlExceptionHandler {
    @ExceptionHandler(LackOfShortenUrlKeyException::class)
    fun handleLackOfShortenUrlKeyException(ex: LackOfShortenUrlKeyException): ResponseEntity<String> {
        return ResponseEntity("단축 URL 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundShortenUrlException::class)
    fun handleNotFoundShortenUrlException(ex: NotFoundShortenUrlException): ResponseEntity<String> {
        return ResponseEntity("단축 URL을 찾지 못했습니다.", HttpStatus.NOT_FOUND)
    }
}