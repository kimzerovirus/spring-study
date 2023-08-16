package me.kzv.helloquerydsl.entity;

import lombok.*;
import me.kzv.helloquerydsl.utils.LongListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString // (of = {"id", "username", "age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 은 EAGER 보다는 LAZY 로 설정하기 -> member 조회 할때는 Team 은 가짜(임시) 객체로 만들어 두고 실제로 필요한 시점에 Team 을 조회해온다.
    @JoinColumn(name = "team_id")
    private Team team;

//    @Convert(converter = LongListConverter.class)
//    private List<Long> teamIds = new ArrayList<>();

    public Member(String username) {
        this(username, 0, null);
    }

    public Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;

        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
