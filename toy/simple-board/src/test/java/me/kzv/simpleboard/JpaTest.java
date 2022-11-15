package me.kzv.simpleboard;

import me.kzv.simpleboard.web.domain.Board;
import me.kzv.simpleboard.web.domain.Member;
import me.kzv.simpleboard.web.domain.enums.BoardType;
import me.kzv.simpleboard.web.repository.BoardRepository;
import me.kzv.simpleboard.web.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaTest {

    /**
     * @Before (JUnit 4), @BeforeEach (JUnit 5)
     * 클래스 내에 존재하는 각각의 @Test 를 실행하기 전에 매번 실행
     * @BeforeClass (JUnit 4), @BeforeAll (JUnit 5)
     * 모든 테스트를 실행하기 전 딱 한번만 실행
     * static 으로 선언해야 함
     */

    private final String boardTestTitle = "test";
    private final String email = "test@eamil.com";

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @BeforeEach
    public void init() {
        Member member = memberRepository.save(new Member("name", "password", email));
        Board board = boardRepository.save(new Board(boardTestTitle, "subtitle", "contents", BoardType.free, member));
    }

    @Test
    @DisplayName("제대로 생성됐는지 테스트")
    public void test(){
        Member member = memberRepository.findByEmail(email);
        assertThat(member.getName()).isEqualTo("name");
        assertThat(member.getPassword()).isEqualTo("password");
        assertThat(member.getEmail()).isEqualTo(email);

        Board board = boardRepository.findByMember(member);
        assertThat(board.getTitle()).isEqualTo(boardTestTitle);
    }

}
