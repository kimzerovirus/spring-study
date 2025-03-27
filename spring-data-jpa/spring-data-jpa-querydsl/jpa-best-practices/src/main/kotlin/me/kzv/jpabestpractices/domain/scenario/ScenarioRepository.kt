package me.kzv.jpabestpractices.domain.scenario

import org.springframework.stereotype.Repository

@Repository
class ScenarioRepository(
    private val aScenarioRepository: AScenarioRepository,
    private val bScenarioRepository: BScenarioRepository,
) {
    fun save(scenario: Scenario): Scenario {
        return when (scenario.type) {
            ScenarioType.A -> aScenarioRepository.save(AScenario.of(scenario))
            ScenarioType.B -> bScenarioRepository.save(BScenario.of(scenario))
        }
    }
}