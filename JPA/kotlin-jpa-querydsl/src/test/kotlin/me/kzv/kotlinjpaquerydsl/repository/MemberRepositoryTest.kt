package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.Member
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class MemberRepositoryTest{

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun testMember() {
        //given
        val member = Member(username = "memberA")

        //when
        val saveId = memberRepository.save(member)
        val findMember = memberRepository.find(saveId)

        //then
        assertThat(findMember.id).isEqualTo(member.id);
        assertThat(findMember.username).isEqualTo(member.username);
        assertThat(findMember).isEqualTo(member);
    }

}