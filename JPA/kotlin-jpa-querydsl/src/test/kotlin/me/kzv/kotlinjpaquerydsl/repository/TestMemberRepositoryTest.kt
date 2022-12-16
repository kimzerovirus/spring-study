package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.TestMember
import org.springframework.boot.test.context.SpringBootTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@Transactional // No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call 하나의 transaction 으로 설정해줘야함
@SpringBootTest
class TestMemberRepositoryTest{

    @Autowired
    private lateinit var memberRepository: TestMemberRepository

    @Test
    @Rollback(false) // 테스트에서는 기본적으로는 rollback 시키지만 이렇게하면 저장됨
    fun testMember() {
        //given
        val member = TestMember(username = "memberA")

        //when
        val saveId = memberRepository.save(member)
        val findMember = memberRepository.find(saveId)

        //then
        assertThat(findMember.id).isEqualTo(member.id);
        assertThat(findMember.username).isEqualTo(member.username);
        assertThat(findMember).isEqualTo(member);
    }

}