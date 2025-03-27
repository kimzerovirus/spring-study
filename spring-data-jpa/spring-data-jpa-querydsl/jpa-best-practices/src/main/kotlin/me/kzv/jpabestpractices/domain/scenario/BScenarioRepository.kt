package me.kzv.jpabestpractices.domain.scenario

import org.springframework.data.jpa.repository.JpaRepository

interface BScenarioRepository : JpaRepository<BScenario, Long> {
}