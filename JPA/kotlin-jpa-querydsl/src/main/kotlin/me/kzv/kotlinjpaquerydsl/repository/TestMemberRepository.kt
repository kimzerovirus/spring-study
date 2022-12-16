package me.kzv.kotlinjpaquerydsl.repository

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import me.kzv.kotlinjpaquerydsl.entity.TestMember
import org.springframework.stereotype.Repository

@Repository
class TestMemberRepository() {

    @PersistenceContext //autowired 하는 방법
    private lateinit var em: EntityManager

    fun save(member: TestMember): Long {
        em.persist(member)
        return member.id!!
    }

    fun find(id: Long): TestMember = em.find(TestMember::class.java, id)

}