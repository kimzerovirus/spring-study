package me.kzv.kotlinjpaquerydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import me.kzv.kotlinjpaquerydsl.entity.QTeamMember
import me.kzv.kotlinjpaquerydsl.entity.Team
import me.kzv.kotlinjpaquerydsl.entity.TeamMember
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
class QuerydslTest {
    @Autowired lateinit var em: EntityManager

    @BeforeEach
    fun before(){
        val teamA = Team(name = "teamA")
        val teamB = Team(name = "teamB")
        em.persist(teamA)
        em.persist(teamB)

        val member1 = TeamMember(name = "member1", age = 10, team = teamA)
        val member2 = TeamMember(name = "member2", age = 20,  team = teamA)
        val member3 = TeamMember(name = "member2", age = 30,  team = teamA)
        val member4 = TeamMember(name = "member2", age = 40,  team = teamA)
        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)
    }

    @Test
    fun `startJPQL`() {
        //given
        val qlString = "select m from TeamMember m where m.name = :name"

        //when
        val findMember = em.createQuery(qlString, TeamMember::class.java)
            .setParameter("name", "member1")
            .singleResult

        //then
       assertThat(findMember.name).isEqualTo("member1")
    }
    
    @Test
    fun `startQuerydsl`() {
        //given
        val queryFactory = JPAQueryFactory(em)
        val m = QTeamMember("m")

        //when
        val findMember = queryFactory
            .select(m)
            .from(m)
            .where(m.name.eq("member1"))
            .fetchOne()

        //then
        assertThat(findMember?.name).isEqualTo("member1")
    }
}