package me.kzv.fullstackdev.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    public static final String PREFIX = "BEARER ";

    private static final long EXPIRATION_TIME = 86400000; // 1day of milli seconds
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 테스트용으로 넣은 값이고 실 운영환경에서는 직접 비밀키 값을 넣어야한다.

    public String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setIssuer("kimzerovirus")
                .signWith(SECRET_KEY)
                .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
        }

        return null;
    }
}
