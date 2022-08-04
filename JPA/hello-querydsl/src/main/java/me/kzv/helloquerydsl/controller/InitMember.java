package me.kzv.helloquerydsl.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.helloquerydsl.entity.Member;
import me.kzv.helloquerydsl.entity.Team;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.stream.IntStream;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        initMemberService.init();
    }

    @Component
    static class InitMemberService{
        @PersistenceContext
        private EntityManager em;

        @Transactional // <- PostConstruct 와 Transactional 은 스프링 라이프 사이클 때문에 같이 사용이 안된다.
        public void init(){
            Team teamA = new Team("teamA");
            Team teamB = new Team("teamB");
            em.persist(teamA);
            em.persist(teamB);

            IntStream.range(1, 100).forEach(i -> {
                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
                em.persist(new Member("member" + i, i, selectedTeam));
            });
        }
    }
}
