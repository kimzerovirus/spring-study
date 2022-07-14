package me.kzv.demo.web.domain;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ClubMember extends BaseTimeEntity{
    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>(); // 역할을 정하는
    // club_member_role_set이라는 테이블이 같이 생성되며
    // club_member_email, role_set 2개의 컬럼을 가지고 있다.

    public void addMemberRole(ClubMemberRole clubMemberRole) {
        roleSet.add(clubMemberRole);
    }
}
