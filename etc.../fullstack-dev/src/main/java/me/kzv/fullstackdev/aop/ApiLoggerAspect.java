package me.kzv.fullstackdev.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Aspect
public class ApiLoggerAspect {
    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;

    @AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)", returning = "returnValue")
    public void logging(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {
        String req = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getParameterMap());
        String res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnValue);

        log.info("[GET] URI: {}", request.getRequestURI());
        log.info("REQ: {}", req);

        if (res.length() > 256) {
            log.info("RES: {} <...omit...>", res.substring(0, 256));
        } else {
            log.info("RES: {}", res);
        }
    }
}
