package architecture.runners;

import me.kzv.architecture.Calculator;
import me.kzv.architecture.runners.CustomTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(CustomTestRunner.class)
public class CalculatorTest {

    // JUnit4 / JUni5 패키지 비교
    // org.junit.Test / org.junit.jupiter.api.Test
    // org.junit.Assert / org.junit.jupiter.api.Assertions

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        assertEquals(60, result, 0);
    }
}
