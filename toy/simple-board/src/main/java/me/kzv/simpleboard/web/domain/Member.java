package me.kzv.simpleboard.web.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.simpleboard.web.domain.enums.SocialType;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;
    private String password;
    private String email;

    private String principal;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public Member(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Builder
    public Member(String name, String password, String email, String principal, SocialType socialType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.principal = principal;
        this.socialType = socialType;
    }
}
