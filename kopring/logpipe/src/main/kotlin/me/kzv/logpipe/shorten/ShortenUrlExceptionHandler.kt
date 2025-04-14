package me.kzv.logpipe.shorten

import me.kzv.logpipe.shorten.exception.LackOfShortenUrlKeyException
import me.kzv.logpipe.shorten.exception.NotFoundShortenUrlException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(assignableTypes = [ShortenUrlApi::class])
class ShortenUrlExceptionHandler {
    private val logger = LoggerFactory.getLogger(ShortenUrlExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidateShortenUrlException(ex: MethodArgumentNotValidException, bindingResult: BindingResult): ResponseEntity<String> {
//        val errorMessage = bindingResult.fieldError?.defaultMessage ?: "잘못된 요청값입니다."
        var errorMessage = ""
        for (fieldError in ex.bindingResult.fieldErrors) {
            errorMessage += "필드 ${fieldError.field} : ${fieldError.defaultMessage}\n"
        }
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(LackOfShortenUrlKeyException::class)
    fun handleLackOfShortenUrlKeyException(ex: LackOfShortenUrlKeyException): ResponseEntity<String> {
        logger.error("단축 URL 자원이 부족합니다")
        return ResponseEntity("단축 URL 자원이 부족합니다.", HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NotFoundShortenUrlException::class)
    fun handleNotFoundShortenUrlException(ex: NotFoundShortenUrlException): ResponseEntity<String> {
        logger.info("단축 URL을 찾지 못했습니다. request key: {}", ex.shortenUrlKey)
        return ResponseEntity("단축 URL을 찾지 못했습니다.", HttpStatus.NOT_FOUND)
    }
}