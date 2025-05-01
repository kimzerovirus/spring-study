package architecture.rules;

import me.kzv.architecture.rules.CustomRule;
import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTester {

    @Rule
    public final CustomRule myRule = new CustomRule();

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
