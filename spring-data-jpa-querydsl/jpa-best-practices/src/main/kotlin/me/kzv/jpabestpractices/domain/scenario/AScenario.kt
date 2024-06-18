package me.kzv.jpabestpractices.domain.scenario

import jakarta.persistence.Entity

@Entity
class AScenario(
    content: String,
    type: ScenarioType
) : Scenario(content = content, type = type) {
    companion object {
        fun of(scenario: Scenario): AScenario {
            return with(scenario) {
                AScenario(content, type)
            }
        }
    }
}