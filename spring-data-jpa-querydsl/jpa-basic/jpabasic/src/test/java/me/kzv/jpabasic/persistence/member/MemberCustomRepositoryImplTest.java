package me.kzv.jpabasic.persistence.member;

import jakarta.transaction.Transactional;
import me.kzv.jpabasic.persistence.member.dto.MemberSearchDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberCustomRepositoryImplTest {
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("jpql 테스크")
    @Test
    public void jpqlTest() throws Exception {
        memberRepository.searchMemberList(new MemberSearchDto());
        memberRepository.searchMemberList(new MemberSearchDto("멤버 이름", "팀 이름", 1, 10));
    }

    @DisplayName("jdbcTemplate 테스크")
    @Test
    public void jdbcTemplate() throws Exception {
        memberRepository.searchMemberListWithJdbcTemplate(new MemberSearchDto());
        memberRepository.searchMemberListWithJdbcTemplate(new MemberSearchDto("멤버 이름", "팀 이름", 1, 10));
    }
}