package me.kzv.demo.security.handler;

import lombok.extern.log4j.Log4j2;
import me.kzv.demo.security.dto.ClubAuthMemberDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private PasswordEncoder passwordEncoder;

    // securityConfig에서 생성할 때 PasswordEncoder를 받아왔다
    public ClubLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("=======success handler=======");

        ClubAuthMemberDto authMember = (ClubAuthMemberDto) authentication.getPrincipal();
        boolean fromSocial = authMember.isFromSocial();
        log.info("Need Modify Member? " + fromSocial);

        boolean passwordResult = passwordEncoder.matches("1111", authMember.getPassword());
        if (fromSocial && passwordResult) {
            redirectStrategy.sendRedirect(request, response, "/sample/member/modify?from=social");
        }
    }
}
