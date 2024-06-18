package me.kzv.jpabestpractices.domain.member

import me.kzv.jpabestpractices.domain.team.Team
import me.kzv.jpabestpractices.domain.team.TeamRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class MemberRepositoryTest @Autowired constructor(
    val memberRepository: MemberRepository,
    val memberReadOnlyRepository: MemberReadOnlyRepository,
    val teamRepository: TeamRepository,
) {
    @Test
    fun `읽기 전용 테스트`() {
        val team = teamRepository.save(Team("teamA"))
        val memberA = Member(team, "A")
        val memberB = Member(team, "B", MemberStatus.INACTIVE)

        teamRepository.save(team)
        memberRepository.saveAll(listOf( memberA, memberB))

        val findAllMember = memberRepository.findAll()
        assertThat(findAllMember.size).isEqualTo(1)

        val findAllReadOnlyMember = memberReadOnlyRepository.findAll()
        assertThat(findAllReadOnlyMember.size).isEqualTo(2)
    }

    @Test
    fun `읽기 전용에 insert시 에러`() {
        val team = teamRepository.save(Team("teamA"))
        val member = MemberReadOnly(team, "A")

        memberReadOnlyRepository.save(member) // insert가 되어버림... readonly가 안되네 좀더 알아 봐야 할듯 - 일단은 같은 테이블을 다양한 클래스로 받는것 까지는 됨
    }
}