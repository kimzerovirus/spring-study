package me.kzv.issue.exception

import com.auth0.jwt.exceptions.TokenExpiredException
import mu.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// 전역 exception 처리
@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ServerException::class)
    fun handlerServerException(ex: ServerException) : ErrorResponse {
        logger.error { ex.message}

        return ErrorResponse(code = ex.code, message = ex.message)
    }

    /** 토큰 Exception */
    @ExceptionHandler(TokenExpiredException::class)
    fun handlerTokenExpiredException(ex: TokenExpiredException) : ErrorResponse {
        logger.error { ex.message}

        return ErrorResponse(code = 401, message = "Token Expired Error")
    }

    /** 정의하지 않은 Exception 발생한 경우 */
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception) : ErrorResponse {
        logger.error { ex.message}

        return ErrorResponse(code = 500, message = "Internal Server Error")
    }


}