package core.disabled

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled("실행 불가능한 테스트 클래스") // 클래스에서 넣은 value는 ide에서 표시되지 않는듯..
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