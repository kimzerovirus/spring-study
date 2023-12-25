package me.kzv.simplesns.exception

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.kzv.simplesns.controller.response.Response
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException


class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = "application/json";
        response.status = ErrorCode.INVALID_TOKEN.status!!.value()
        response.writer.write(Response.error(ErrorCode.INVALID_TOKEN.name).toString())
    }
}