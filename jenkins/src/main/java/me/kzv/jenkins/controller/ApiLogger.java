package me.kzv.jenkins.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class ApiLogger {
    private final HttpServletRequest request;
    private final ObjectMapper objectMapper;

    //    @After("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//    public void logGet() throws JsonProcessingException {
//        var params = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getParameterMap()); // json 예쁘게 정렬됨
////        var params = objectMapper.writer().writeValueAsString(request.getParameterMap());
//        log.info("[GET] {}, {}", request.getRequestURI(), params);
//    }
//
//    @After("@annotation(org.springframework.web.bind.annotation.PostMapping)")
//    public void logPost(JoinPoint joinPoint) throws JsonProcessingException {
//        var result = (MethodInvocationProceedingJoinPoint) joinPoint;
//        var field = Arrays.stream(MethodInvocationProceedingJoinPoint.class.getDeclaredFields())
//                .filter(f -> f.getName().equals("methodInvocation"))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Field not found"));
//
//        field.setAccessible(true);
//
//        var methodInvocation = (MethodInvocation) ReflectionUtils.getField(field, result);
//        assert methodInvocation != null;
//
//        List<Long> argIndexes = List.of(
//                Arrays.stream(methodInvocation.getMethod().getParameters())
//                        .filter(p -> p.getDeclaredAnnotations().length > 0 && p.getDeclaredAnnotations()[0] instanceof RequestBody)
//                        .count()
//        );
//
//        var args = result.getArgs();
//        System.out.println(Arrays.toString(args));
//
//        String body = objectMapper.writeValueAsString(args);
//        log.info("[POST] {}, {}", request.getRequestURI(), body);
//    }
    @AfterReturning(value = "@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)", returning = "returnValue")
    public void logging(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {
        // joinPoint.getArgs() -> 필드명을 알수가 없음 그냥 parameterMap에서 꺼내는게 나은듯
        String req = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getParameterMap());
        String res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnValue);

//        if(returnValue instanceof ResponseEntity responseEntity){
//            res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getBody());
//        } else {
//            res = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnValue);
//        }

        log.info("[GET] URI: {}", request.getRequestURI());
        log.info("REQ: {}", req);

        if (res.length() > 256) {
            log.info("RES: {} <...omit...>", res.substring(0, 256));
        } else {
            log.info("RES: {}", res);
        }
    }
}
