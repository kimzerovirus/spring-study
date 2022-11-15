package me.kzv.simpleboard;

import me.kzv.simpleboard.web.domain.Board;
import me.kzv.simpleboard.web.domain.Member;
import me.kzv.simpleboard.web.domain.enums.BoardType;
import me.kzv.simpleboard.web.repository.BoardRepository;
import me.kzv.simpleboard.web.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class SimpleBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleBoardApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MemberRepository memberRepository, BoardRepository boardRepository) throws Exception {
        return args -> {
            Member member = memberRepository.save(new Member("테스트멤버1", "1234", "test@email.com"));

            IntStream.rangeClosed(1, 200).forEach(idx -> boardRepository.save(new Board(
                    "테스트 게시글" + idx, "서브타이틀" + idx, "내용" + idx, idx % 2 > 0 ? BoardType.free : BoardType.notice, member
            )));
        };
    }
}
