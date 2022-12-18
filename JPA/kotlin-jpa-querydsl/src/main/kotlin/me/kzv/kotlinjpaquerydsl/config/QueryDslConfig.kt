package me.kzv.kotlinjpaquerydsl.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

@Configuration
class QueryDslConfig {

    @PersistenceContext
    lateinit var em: EntityManager

    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}