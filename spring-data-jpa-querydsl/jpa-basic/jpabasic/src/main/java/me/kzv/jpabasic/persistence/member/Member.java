package me.kzv.jpabasic.persistence.member;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.persistence.team.Team;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String memberName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // 실제 db에 물리적으로 fk를 건다.
    private Team team;
}
