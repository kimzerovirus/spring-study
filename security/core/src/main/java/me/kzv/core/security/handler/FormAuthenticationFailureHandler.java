package me.kzv.core.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 아이디가 없거나 패스워드가 일치하지 않은 경우 호출된다.
        String errorMessage = "Invalid Username or Password";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username or Password";
        }else if(exception instanceof InsufficientAuthenticationException){
            errorMessage = "Invalid Secret Key";
        }

        String targetUrl = UriComponentsBuilder.fromUriString("/login").queryParam("error", "true").queryParam("exception", exception.getMessage()).build().toString();

//        setDefaultFailureUrl("/login?error=true&exception=" + exception.getMessage());
        setDefaultFailureUrl(targetUrl);
        log.info(targetUrl);

        request.setCharacterEncoding("utf-8");

        // 부모에게 위임하여 처리
        super.onAuthenticationFailure(request, response, exception);
    }
}
