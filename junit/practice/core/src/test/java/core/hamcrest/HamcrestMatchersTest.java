package core.hamcrest;

import me.kzv.core.hamcrest.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

public class HamcrestMatchersTest {

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";
    private static final  Customer customer = new Customer(FIRST_NAME, LAST_NAME);


    @Test
    @DisplayName("Hamcrest is, anyOf, allOf")
    public void testHamcrestIs() {
        int price1 = 1, price2 = 1, price3 = 2;

        assertThat(1, is(price1));
        assertThat(1, anyOf(is(price2), is(price3)));
        assertThat(1, allOf(is(price1), is(price2)));
    }

    @Test
    @DisplayName("Null expected")
    void testNull() {
        assertThat(null, nullValue());
    }

    @Test
    @DisplayName("Object expected")
    void testNotNull() {
        assertThat(customer, notNullValue());
    }

    @Test
    @DisplayName("Check correct customer properties")
    void checkCorrectCustomerProperties() {
        assertThat(customer, allOf(
                hasProperty("firstName", is(FIRST_NAME)),
                hasProperty("lastName", is(LAST_NAME))
        ));
    }

}
