package core.assertions.junint;

import me.kzv.core.assertions.SUT;
import me.kzv.core.assertions.Job;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class AssertTimeoutTest {
    private final SUT systemUnderTest = new SUT("테스트 대상 시스템");

    // 지정한 시간 안에 executable 객체가 테스트를 완료했는지 판단하여 성공 완료 여부를 결정한다.
    // 즉, 일정 시간 안에 테스트를 통과하는지 확인할 때 사용

    @Test
    @DisplayName("작업을 마칠 때까지 기다리는 assertTimeout 메서드")
    void testTimeout() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeout(ofMillis(500), () -> systemUnderTest.run(200));
    }

    @Test
    @DisplayName("시간이 지나면 작업을 중지시키는 assertTimeoutPreemptively 메서드")
    void testTimeoutPreemptively() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeoutPreemptively(ofMillis(500), () -> systemUnderTest.run(200));
    }
}
