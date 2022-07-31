package com.example.jwttutorial.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserPrincipal implements OAuth2User, UserDetails {
    private User user;
    private List<GrantedAuthority> authorities;
    private Map<String, Object> oauthUserAttributes;

    private UserPrincipal(User user, List<GrantedAuthority> authorities,
                          Map<String, Object> oauthUserAttributes) {
        this.user = user;
        this.authorities = authorities;
        this.oauthUserAttributes = oauthUserAttributes;
    }

    /**
     * OAuth2 로그인시 사용
     */
    public static UserPrincipal create(User user, Map<String, Object> oauthUserAttributes) {
        return new UserPrincipal(user, List.of(() -> "ROLE_USER"), oauthUserAttributes);
    }

    public static UserPrincipal create(User user) {
        return new UserPrincipal(user, List.of(() -> "ROLE_USER"), new HashMap<>());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getEmail());
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
        return String.valueOf(user.getEmail());
    }
}
