package me.kzv.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(TisException.class)
    public ResponseEntity<String> handle(Exception ex) {
        return new ResponseEntity<>(messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchMessageException.class)
    public String handleMsgError(Exception ex) {
        return ex.getMessage();
    }
}