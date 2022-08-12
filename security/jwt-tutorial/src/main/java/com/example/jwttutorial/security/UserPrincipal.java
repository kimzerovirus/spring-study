package com.example.jwttutorial.security;

import com.example.jwttutorial.entity.Member;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class UserPrincipal implements OAuth2User, UserDetails {
    private Member member;
    private List<GrantedAuthority> authorities;
    private Map<String, Object> oauthUserAttributes;

    private UserPrincipal(Member member, List<GrantedAuthority> authorities,
                          Map<String, Object> oauthUserAttributes) {
        this.member = member;
        this.authorities = authorities;
        this.oauthUserAttributes = oauthUserAttributes;
    }

    /**
     * OAuth2 로그인시 사용
     */
    public static UserPrincipal create(Member member, Map<String, Object> oauthUserAttributes) {
        return new UserPrincipal(member, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static UserPrincipal create(Member member) {
        return new UserPrincipal(member, List.of(() -> "ROLE_USER"), new HashMap<>());
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
//        return String.valueOf(member.getEmail()));
        return String.valueOf(member.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(oauthUserAttributes);
    }

    @Override
    @Nullable
    @SuppressWarnings("unchecked")
    public <A> A getAttribute(String name) {
        return (A) oauthUserAttributes.get(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorities);
    }

    @Override
    public String getName() {
//        return String.valueOf(member.getEmail());
        return String.valueOf(member.getId());
    }
}
