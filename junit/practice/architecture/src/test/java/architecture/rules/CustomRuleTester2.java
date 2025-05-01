package architecture.rules;

import me.kzv.architecture.rules.CustomRule;
import org.junit.Rule;
import org.junit.Test;

public class CustomRuleTester2 {

    private final CustomRule myRule = new CustomRule();

    @Rule
    public CustomRule getMyRule() {
        return myRule;
    }

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
