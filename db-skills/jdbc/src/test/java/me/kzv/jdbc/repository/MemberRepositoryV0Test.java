package me.kzv.jdbc.repository;

import lombok.extern.slf4j.Slf4j;
import me.kzv.jdbc.domain.Member;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void create() throws SQLException {
        Member member = new Member("testMember", 10000);
        repository.save(member);
    }

    @Test
    void read() throws SQLException{
        Member member = new Member("testMember", 10000);

        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member == findMember {}", member == findMember); // false
        log.info("member equals findMember {}", member.equals(findMember)); // true
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void update() throws SQLException{
        Member member = new Member("testMember", 10000);

        repository.update(member.getMemberId(),20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

    }

    @Test
    void delete() throws SQLException{
        Member member = new Member("testMember", 20000);

        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}