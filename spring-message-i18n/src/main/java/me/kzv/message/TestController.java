package me.kzv.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class TestController {

    private final MessageSource messageSource;

    @GetMapping("/test-error")
    public Object test(boolean error) {
        log.info(error);

        if (error) {
            throw new TisException(TisError.UNKNOWN);
        }

        log.info("exception 안터졌지롱!");

        return ResponseEntity.ok("error.unknown");
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("현재 Locale 정보 : " + LocaleContextHolder.getLocale());
        return messageSource.getMessage("say.hello", null, LocaleContextHolder.getLocale());
    }

    @GetMapping("/test")
    public Object test2() {
        return "test";
    }
}
