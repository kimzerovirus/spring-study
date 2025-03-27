package me.kzv.jpabestpractices.domain.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {
}