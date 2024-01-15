package me.kzv.jpabestpractices.supports

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> =
        AuditorAware {
            Optional.of(
                // 시큐리티가 적용된 프로젝트라면 시큐리티에서 로그인한 유저의 정보를 가져와 이름을 표시해준다.
//                SecurityContextHolder.getContext().authentication.name ?: DEFAULT_USER_NAME,
                "ANONYMOUS"
            )
        }

    @Bean
    fun jpaQueryFactory(entityManager: EntityManager) = JPAQueryFactory(entityManager)
}