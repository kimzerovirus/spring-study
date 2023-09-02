package me.kzv.datajpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(of = {"id", "username", "age"}) // 연관관계 필드는 무한루프가 될 수 있는 위험이 있어 안하는게 낫다.
@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
)
@NamedEntityGraph(
        name="Member.all",
        attributeNodes = @NamedAttributeNode("team")
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne 은 EAGER 보다는 LAZY 로 설정하기 -> member 조회 할때는 Team 은 가짜(임시) 객체로 만들어 두고 실제로 필요한 시점에 Team 을 조회해온다.
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Team team) {
        this.username = username;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;

        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
