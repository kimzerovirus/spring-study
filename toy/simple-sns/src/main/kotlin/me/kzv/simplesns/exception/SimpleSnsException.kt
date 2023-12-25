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

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not founded"),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),

    ALREADY_LIKED_POST(HttpStatus.CONFLICT, "user already like the post"),

    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission"),

    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurs"),

    NOTIFICATION_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Connect to notification occurs error"),
}