package core.assumptions;

import me.kzv.core.assumptions.Job;
import me.kzv.core.assumptions.SUT;
import me.kzv.core.assumptions.environment.JavaSpecification;
import me.kzv.core.assumptions.environment.OperationSystem;
import me.kzv.core.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

// 가정문
// 테스트를 수행하는 데 필수인 전제 조건이 참인지 검증하는 테스트이다.

class AssumptionsTest {
    private static final String EXPECTED_JAVA_VERSION = "21";
    private final TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );

    private final SUT systemUnderTest = new SUT();

    @BeforeEach
    void setUp() {
        assumeTrue(!environment.isWindows());
    }

    @Test
    void testNoJobToRun() {
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(systemUnderTest.hasJobToRun()));
    }

    @Test
    void testJobToRun() {
        assumeTrue(!environment.isWindows());

        systemUnderTest.run(new Job());

        assertTrue(systemUnderTest.hasJobToRun());
    }
}
