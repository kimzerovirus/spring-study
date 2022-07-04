package me.kzv.demo.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDto extends User implements OAuth2User {
    // username은 스프링 시큐리티에서 회원을 구별하는 아이디로 사용된다.(식별 데이터로 정해져 있음, 스프링시큐리티에서 일종의 id라고 보면됨)

    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String, Object> attr;

    public ClubAuthMemberDto(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    public ClubAuthMemberDto(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    @Override
    public Map<String, Object> getAttributes(){
        return this.attr;
    }

    // OAuth2User는 Map타입으로 모든 인증 결과를 attributes라는 이름으로 가지고 있다.
}
