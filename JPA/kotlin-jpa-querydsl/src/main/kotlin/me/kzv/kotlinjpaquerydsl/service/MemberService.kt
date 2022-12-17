package me.kzv.kotlinjpaquerydsl.service

import jakarta.persistence.EntityNotFoundException
import me.kzv.kotlinjpaquerydsl.entity.Member
import me.kzv.kotlinjpaquerydsl.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {
    // 회원 가입
    @Transactional
    fun join(member: Member): Long{
        validateDuplicateMember(member)
        memberRepository.save(member)
        return member.id!!
    }

    private fun validateDuplicateMember(member: Member) {
        // Exception
        val findMembers = memberRepository.findByName(member.name)
        if (findMembers.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다")
        }
    }

    // 회원 전체 조회
    fun findMembers(): List<Member> = memberRepository.findAll()

    // 단일 회원 조회
    fun findOne(memberId: Long): Member = memberRepository.findByIdOrNull(memberId) ?: throw EntityNotFoundException("아이디 - $memberId (은)는 존재하지 않습니다.")
}