package me.kzv.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import me.kzv.exception.exception.UserException;
import me.kzv.exception.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "me.kzv.exception.api")
public class ExControllerAdvice {

    // 현재 클래스에서 IllegalArgumentException 에러가 터지면 이 메서드가 실행된다.
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 이게 없으면 200 상태로 넘어가게 된다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[ExceptionHandler] ",e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[UserExceptionHandler] ", e);
        ErrorResult err = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult internalExHandler(Exception e) {
        log.error("[ex] ",e);
        return new ErrorResult("EX", "내부 오류");
    }
}
