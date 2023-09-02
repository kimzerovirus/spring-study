package me.kzv.datajpa.repository;

import me.kzv.datajpa.entity.Member;
import me.kzv.datajpa.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@Transactional
@SpringBootTest
class TeamRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    public void create(){
        Team team = new Team("팀1");
        Member member1 = new Member("유저1", team);
        Member member2 = new Member("유저2", team);
        Member member3 = new Member("유저3", team);
        teamRepository.save(team);
        memberRepository.saveAll(Arrays.asList(member1, member2, member3));
    }

    @Test
    public void entityGraph_테스트() throws Exception {
        Team team = teamRepository.findByName("팀1").orElseThrow(EntityNotFoundException::new);
        System.out.println(team);
        for (Member member: team.getMembers()) {
            System.out.println(member);
        }

        /**
         *     select
         *         team0_.team_id as team_id1_2_0_,
         *         members1_.member_id as member_i1_1_1_,
         *         team0_.name as name2_2_0_,
         *         members1_.age as age2_1_1_,
         *         members1_.team_id as team_id4_1_1_,
         *         members1_.username as username3_1_1_,
         *         members1_.team_id as team_id4_1_0__,
         *         members1_.member_id as member_i1_1_0__
         *     from
         *         team team0_
         *     left outer join
         *         member members1_
         *             on team0_.team_id=members1_.team_id
         *     where
         *         team0_.name=?
         */
    }

    @Test
    public void findAll_entityGraph_테스트() throws Exception {
        teamRepository.findAll(); // select t.*, m.age from team, member t left outer join member m on t.team_id = m.team_id
        /**
         * select
         *         team0_.team_id as team_id1_2_0_,
         *         members1_.member_id as member_i1_1_1_,
         *         team0_.name as name2_2_0_,
         *         members1_.age as age2_1_1_,
         *         members1_.team_id as team_id4_1_1_,
         *         members1_.username as username3_1_1_,
         *         members1_.team_id as team_id4_1_0__,
         *         members1_.member_id as member_i1_1_0__
         *     from
         *         team team0_
         *     left outer join
         *         member members1_
         *             on team0_.team_id=members1_.team_id
         */
    }
}