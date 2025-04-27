package core.assertions.assertj;

import me.kzv.core.assertions.NoJobException;
import me.kzv.core.assertions.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssertThrowsTest {
    private final SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("예외가 발생하는지 검증한다")
    void testExpectedException() {
        assertThatThrownBy(systemUnderTest::run)
                .isExactlyInstanceOf(NoJobException.class);
    }

    @Test
    @DisplayName("예외가 발생하고 예외에 대한 참조가 유지되는지 검증한다")
    void testCatchException() {
        assertThatThrownBy(() -> systemUnderTest.run(1000))
                .isInstanceOf(NoJobException.class)
                .hasMessageContaining("작업")
                .hasMessage("실행할 작업이 없습니다!");
    }
}


