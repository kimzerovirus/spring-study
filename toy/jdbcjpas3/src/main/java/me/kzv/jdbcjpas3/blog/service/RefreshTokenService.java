package me.kzv.jdbcjpas3.blog.service;

import lombok.RequiredArgsConstructor;
import me.kzv.jdbcjpas3.blog.domain.RefreshToken;
import me.kzv.jdbcjpas3.blog.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
