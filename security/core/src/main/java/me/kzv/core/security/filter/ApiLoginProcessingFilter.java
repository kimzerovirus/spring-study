package me.kzv.core.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kzv.core.domain.AccountDto;
import me.kzv.core.security.token.ApiAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public ApiLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/login")); // <- 이 url로 요청시 intercept
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(isApi(request)){
            throw new IllegalStateException("Authentication is not supported");
        }

        AccountDto dto = objectMapper.readValue(request.getReader(), AccountDto.class);

        if (!StringUtils.hasText(dto.getUsername()) || !StringUtils.hasText(dto.getPassword())) { // hasText return (str != null && !str.isEmpty() && containsText(str))
            throw new IllegalArgumentException("Username or Password is Empty");
        }

        ApiAuthenticationToken apiAuthenticationToken = new ApiAuthenticationToken(dto.getUsername(), dto.getPassword());

        return getAuthenticationManager().authenticate(apiAuthenticationToken);
    }

    private boolean isApi(HttpServletRequest request) {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-with"))) {
            return true;
        }
        return false;
    }


}
