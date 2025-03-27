package me.kzv.jpabestpractices.domain.scenario

import jakarta.persistence.Entity

@Entity
class BScenario(
    content: String,
    type: ScenarioType
) : Scenario(content = content, type = type) {
    companion object {
        fun of(scenario: Scenario): BScenario {
            return with(scenario) {
                BScenario(content, type)
            }
        }
    }
}