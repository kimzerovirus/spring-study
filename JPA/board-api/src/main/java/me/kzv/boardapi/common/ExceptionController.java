package me.kzv.boardapi.common;

import lombok.RequiredArgsConstructor;
import me.kzv.boardapi.security.exception.CustomAuthenticationEntryPointException;
import me.kzv.boardapi.web.dto.CommonResultDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResultDto entrypointException() {
        throw new CustomAuthenticationEntryPointException();
    }

    @GetMapping(value = "/accessdenied")
    public CommonResultDto accessdeniedException() {
        throw new AccessDeniedException("");
    }
}
