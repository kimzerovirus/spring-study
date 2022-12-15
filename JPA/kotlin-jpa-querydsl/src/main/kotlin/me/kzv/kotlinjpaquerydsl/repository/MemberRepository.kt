package me.kzv.kotlinjpaquerydsl.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import me.kzv.kotlinjpaquerydsl.entity.Member
import org.springframework.stereotype.Repository

@Repository
class MemberRepository() {

    @PersistenceContext //autowired 하는 방법
    private lateinit var em: EntityManager

    fun save(member: Member): Long {
        em.persist(member)
        return member.id!!
    }

    fun find(id: Long): Member = em.find(Member::class.java, id)

}