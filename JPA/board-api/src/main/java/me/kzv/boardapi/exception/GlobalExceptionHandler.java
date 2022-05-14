package me.kzv.boardapi.exception;

import lombok.RequiredArgsConstructor;
import me.kzv.boardapi.dto.CommonResultDto;
import me.kzv.boardapi.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseService responseService;

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResultDto defaultException(HttpServletRequest request, Exception e) {
//        return responseService.getFailResult();
//    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResultDto customUserNotFoundException(HttpServletRequest request, CustomUserNotFoundException e) {
        return responseService.getFailResult();
    }
}
/*
    1. @RestControllerAdvice // @ControllerAdvice
        둘의 차이는 에러 발생시 결과를 json으로 반환할것인가의 차이
        특정 패키지 하위의 컨트롤러에만 적용되게도 할 수 있다.
        ex) @RestControllerAdvice(basePackages = “me.kzv.api”)

    2. @ExceptionHandler(Exception.class)
        Exception 발생시 Handler로 처리하겠다고 명시하는것
        Exception이 발생할때 handler를 적용할 것인지 Exception Class를 인자로 넣는다.
        Exception.class로 지정하였는데 Exception.class는 최상위 예외처리 객체이므로 다른 ExceptionHandler에서
        걸러지지 않은 예외가 있으면 최종으로 이 handler를 거쳐 처리된다.
 */