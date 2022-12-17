package me.kzv.kotlinjpaquerydsl.service

import jakarta.persistence.EntityManager
import me.kzv.kotlinjpaquerydsl.entity.Address
import me.kzv.kotlinjpaquerydsl.entity.Member
import me.kzv.kotlinjpaquerydsl.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

// Junit with Kotlin 참고 - https://www.baeldung.com/kotlin/junit-5-kotlin
// Junit 버전 참고 - https://www.whiteship.me/springboot-no-more-runwith/
@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired lateinit var memberService: MemberService
    @Autowired lateinit var memberRepository: MemberRepository

    val address = Address(city = "seoul", street = "seoul univ.", zipcode = "1541")

    @Test
    fun `회원 가입`() {
        //given
        val member = Member(name = "jo", address = address)

        //when
        val savedId = memberService.join(member)

        //then
        assertEquals(member, memberRepository.findByIdOrNull(savedId))
    }

    //    @Test(expected = IllegalStateException.class) -> junit4
    // 테스트가 실패한다면 혹시나 rollback 이 안되어 멤버가 남아있어 expect 지점까지 가기전에 exception 이 나오는지 확인해 보자
    @Test
    fun `회원 가입 실패`() {
        //given
        val member1 = Member(name = "kim", address = address)
        val member2 = Member(name = "kim", address = address)

        //when
        memberService.join(member1)

        //then
        val exception = assertThrows(IllegalStateException::class.java) { memberService.join(member2) }
        assertEquals("이미 존재하는 회원입니다", exception.message)
    }

}