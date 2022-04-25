package me.kzv.demo.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDto extends User {
    // username은 스프링 시큐리티에서 회원을 구별하는 아이디로 사용된다.(식별 데이터로 정해져 있음, 스프링시큐리티에서 일종의 id라고 보면됨)

    private String email;
    private String name;
    private boolean fromSocial;

    public ClubAuthMemberDto(String username, String password,boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }


}
