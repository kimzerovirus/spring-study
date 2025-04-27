package core.assertions.assertj;

import me.kzv.core.assertions.SUT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertThatTest {
    @Test
    @DisplayName("테스트 대상 시스템을 검증하지 않았다")
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        assertThat(systemUnderTest.getSystemName()).isEqualTo("테스트 대상 시스템");
        assertThat(systemUnderTest.isVerified()).isFalse();
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증한다")
    void testSystemUnderVerification() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();

        assertThat(systemUnderTest.getSystemName()).isEqualTo("테스트 대상 시스템");
        assertThat(systemUnderTest.isVerified()).isTrue();
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증한다")
    void testSystemUnderVerification2() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();

        assertThat(systemUnderTest.isVerified()).isTrue();
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증하지 않았다")
    void testSystemNotUnderVerification3() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        assertThat(systemUnderTest.isVerified()).isFalse();
    }

    @Test
    @DisplayName("테스트 대상 시스템은 현재 작업이 없다")
    void testNoJob() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        assertThat(systemUnderTest.getCurrentJob()).isNull();
    }
}
