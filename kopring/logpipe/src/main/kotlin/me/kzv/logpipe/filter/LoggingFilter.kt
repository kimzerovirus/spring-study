package me.kzv.logpipe.filter

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class LoggingFilter : Filter {
    private val logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if (request is HttpServletRequest) {
            val wrappedRequest = CachedBodyHttpServletRequest(request)
            logger.trace(
                "Request : URL: {}, Method: {}, body: {}",
                request.requestURL,
                request.method,
                getRequestBody(wrappedRequest)
            )
            chain.doFilter(wrappedRequest, response)
        } else {
            chain.doFilter(request, response)
        }
    }

    private fun getRequestBody(request: HttpServletRequest): String {
        try {
            return request.reader.lines().reduce("", String::plus)
        } catch (e: IOException) {
            logger.error(e.message, e)
            return "Unable to read request body"
        }
    }
}