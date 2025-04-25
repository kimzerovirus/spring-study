package core.lifecycle;

import me.kzv.core.lifecycle.ResourceForAllTests;
import me.kzv.core.lifecycle.SUT;
import org.junit.jupiter.api.*;

public class SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

    @BeforeAll
    static void setUpClass() {
        // 전체 테스트가 실행되기 전에 한번 실행
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

    @AfterAll
    static void tearDownClass() {
        // 전체 테스트가 끝나기 전에 한번
        resourceForAllTests.close();
    }

    @BeforeEach
    void setUp() {
        // 각 테스트가 실행되기 전에 실행
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

    @AfterEach
    void tearDown() {
        // 각 테스트가 끝나기 전에 실행
        systemUnderTest.close();
    }

    @Test
    void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();
        Assertions.assertTrue(canReceiveRegularWork);
    }

    @Test
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        Assertions.assertFalse(canReceiveAdditionalWork);
    }
}
