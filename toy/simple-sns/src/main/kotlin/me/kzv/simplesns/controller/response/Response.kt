package me.kzv.simplesns.controller.response

// http://tcpschool.com/java/java_generic_various
class Response<T>(
    val resultCode: String,
    val result: T,
) {
    companion object {
        fun error(errorCode: String): Response<Unit?> = Response(errorCode, null)

        fun <T> success(result: T): Response<T> = Response("SUCCESS", result)
    }

}