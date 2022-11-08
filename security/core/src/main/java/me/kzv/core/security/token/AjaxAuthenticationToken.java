package me.kzv.core.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

// UsernamePasswordAuthenticationToken 참조하여 만듦
public class AjaxAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private Object credentials;

    public AjaxAuthenticationToken(Object principal, Object credentials) {
        // 인증을 받기 전에 사용자가 입력한 값
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public AjaxAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities ) {
        // 인증 이후에 결과를 담는 생성자
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }

    public Object getPrincipal() {
        return principal;
    }

    public Object getCredentials() {
        return credentials;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException{
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
