package me.kzv.core.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 별도로 authenticationSuccessHandler 를 지정하지 않으면
 * 기본적으로 org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler 를 사용하게 된다.
 */

@Component
public class FormAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SavedRequest saveRequest = requestCache.getRequest(request,response); // 사용자가 요청을 했던 정보들이 있는 객체
        if (saveRequest != null) { // 다른 자원에 요청을 했다가 거절되서 로그인페이지로 와서 로그인 한 경우 null이다
            String targetUrl = saveRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }

    }
}
