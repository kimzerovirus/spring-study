package me.kzv.simplesns.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class SimpleSnsException(val errorCode: ErrorCode = ErrorCode.BAD_REQUEST) : RuntimeException(errorCode.message)

enum class ErrorCode(
    val status: HttpStatus? = null,
    val message: String? = null
) {
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(HttpStatus.BAD_REQUEST),

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),

}