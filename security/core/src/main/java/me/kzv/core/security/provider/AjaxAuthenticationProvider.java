package me.kzv.core.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.core.security.common.FormWebAuthenticationDetails;
import me.kzv.core.security.service.AccountContext;
import me.kzv.core.security.token.AjaxAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Component
@RequiredArgsConstructor
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 검증을 위한 로직
        String username = authentication.getName();
        String password = (String) authentication.getCredentials(); // Object Type
        log.info(password);

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, accountContext.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않음!!!");
        }

        FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = formWebAuthenticationDetails.getSecretKey();
        log.info(secretKey);
        if (secretKey == null || !"secret".equals(secretKey)) { // input name secret_key의 값이 없거나 값이 secret이 아니라면 에러 발생
            log.error("secret error");
            throw new InsufficientAuthenticationException("InsufficientAuthenticationException");
        }

        AjaxAuthenticationToken authenticationToken = new AjaxAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 토큰 타입이 같을 때 provider 가 작동한다.
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
