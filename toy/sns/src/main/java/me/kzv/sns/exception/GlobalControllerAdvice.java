package me.kzv.sns.exception;

import lombok.extern.slf4j.Slf4j;
import me.kzv.sns.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(SnsApplicationException.class)
    public ResponseEntity<?> applicationHandler(SnsApplicationException e) {
        log.error("Error occurs {}", e.toString());
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name()));
    }
}


/**
 * name()
 * 열거 객체의 문자열 리턴
 *
 * Season season = Season.SPRING;
 * String name = season.name(); // "SPRING"
 */