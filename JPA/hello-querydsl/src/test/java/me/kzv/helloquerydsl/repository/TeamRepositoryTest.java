package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.Member;
import me.kzv.helloquerydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamRepositoryTest {

    @Autowired TeamRepository teamRepository;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    void init(){
        Team team1 = new Team("최상위 부서", null);
        Team savedTeam1 = teamRepository.save(team1);
        Team team2 = new Team("하위 부서", savedTeam1.getId());
        Team savedTeam2 = teamRepository.save(team2);
        Team team3 = new Team("최하위 부서", savedTeam2.getId());
        Team savedTeam3 = teamRepository.save(team3);

        Member superAdmin = new Member("최고 관리자", savedTeam1);
        Member admin = new Member("관리자", savedTeam2);
        Member member = new Member("멤버", savedTeam3);

        Member savedMember1 = memberRepository.save(superAdmin);
        Member savedMember2 = memberRepository.save(admin);
        Member savedMember3 = memberRepository.save(member);
    }

    @Test
    void test() {
//        Team team1 = new Team("최상위 부서", null);
//        Team savedTeam1 = teamRepository.save(team1);
//        Team team2 = new Team("하위 부서", savedTeam1.getId());
//        Team savedTeam2 = teamRepository.save(team2);
//        Team team3 = new Team("최하위 부서", savedTeam2.getId());
//        Team savedTeam3 = teamRepository.save(team3);
//
//        Member superAdmin = new Member("최고 관리자", savedTeam1);
//        Member admin = new Member("관리자", savedTeam2);
//        Member member = new Member("멤버", savedTeam3);
//
//        Member savedMember1 = memberRepository.save(superAdmin);
//        Member savedMember2 = memberRepository.save(admin);
//        Member savedMember3 = memberRepository.save(member);

//        System.out.println(savedMember1);
//        System.out.println(savedMember2);
//        System.out.println(savedMember3);
    }

    @Test
    public void test2() throws Exception {
        List<Team> teamWithMemberByTeamId = teamRepository.findTeamWithMemberByTeamId(2L);
        for (Team team : teamWithMemberByTeamId) {
            System.out.println(team);
        }
    }
}