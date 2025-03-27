package me.kzv.jpabestpractices.domain.scenario

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScenarioRepositoryTest @Autowired constructor(
    val scenarioRepository: ScenarioRepository
){
    @Test
    fun saveTest() {
        val aScenario = Scenario("a scenario", ScenarioType.A)
        val bScenario = Scenario("b scenario", ScenarioType.B)

        val savedA = scenarioRepository.save(aScenario)
        val savedB = scenarioRepository.save(bScenario)

        assertThat(savedA is AScenario).isTrue()
        assertThat(savedB is BScenario).isTrue()
    }
}