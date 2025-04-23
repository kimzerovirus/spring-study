package me.kzv.core.disabled

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("실행 불가능한 테스트 클래스")
class DisabledTest {

    @Disabled("실행 불가능한 테스트 메서드1")
    @Test
    fun `실행 불가능한 테스트1`() {
        println("실행X")
    }

    @Disabled("실행 불가능한 테스트 메서드2")
    @Test
    fun `실행 불가능한 테스트2`() {
        println("실행X")
    }
}