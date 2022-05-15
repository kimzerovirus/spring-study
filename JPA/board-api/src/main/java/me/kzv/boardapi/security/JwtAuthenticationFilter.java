package me.kzv.boardapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// JWT가 유횻한 토큰인지 인증하기 위한 Filter
// 이 필터는 시큐리티의 UsernamePasswordAuthenticationFilter앞에 세팅한다
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    // 생성자 주입해줌 -> final로 @RequiredArgsConstructor
//    public JwtAuthenticationFilter(TokenProvider tokenProvider) {
//        this.tokenProvider = tokenProvider;
//    }

    // Request로 들어오는 JWT Token의 유효성을 검증하는 필터를 필터체인에 등록한다


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenProvider.resolveToken((HttpServletRequest) request);
        if (token != null && tokenProvider.validateToken(token)) {
            Authentication auth = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
