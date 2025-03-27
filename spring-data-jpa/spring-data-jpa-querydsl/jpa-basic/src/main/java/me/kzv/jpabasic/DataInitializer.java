package me.kzv.jpabasic;

import lombok.RequiredArgsConstructor;
import me.kzv.jpabasic.domain.team.Team;
import me.kzv.jpabasic.domain.team.TeamRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final TeamRepository teamRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 서버 구동 시 초기화 로직 작성
        teamRepository.save(Team.builder().teamName("드림팀").build());
    }
}
