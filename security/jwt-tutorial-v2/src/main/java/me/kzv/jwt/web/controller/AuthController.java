package me.kzv.jwt.web.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.jwt.web.controller.dto.JwtRefreshRequestDto;
import me.kzv.jwt.web.controller.dto.JwtResponseDto;
import me.kzv.jwt.web.service.RefreshTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public JwtResponseDto refreshJwt(@RequestBody JwtRefreshRequestDto refreshRequestDto) {
        return refreshTokenService.refreshToken(refreshRequestDto);
    }

}