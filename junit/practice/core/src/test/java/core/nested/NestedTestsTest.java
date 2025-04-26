package core.nested;

import me.kzv.core.nested.Customer;
import me.kzv.core.nested.Gender;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class NestedTestsTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";

    // inner class 내부 클래스를 테스트 할 때 사용
    // 두 개의 클래스가 지나치게 결합도가 높을 때 내부 클래스와 외부 클래스로 만들어
    // 내부 클래스에서 외부 클래스의 모든 인스턴스 변수에 접근할 수 있도록 하여 테스트한다.

    @Nested
    class BuilderTest {
        private final String MIDDLE_NAME = "Michael";

        @Test
        void customerBuilder() throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date customerDate = simpleDateFormat.parse("04-21-2019");

            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .withMiddleName(MIDDLE_NAME)
                    .withBecomeCustomer(customerDate)
                    .build();

            assertAll(() -> {
                assertEquals(Gender.MALE, customer.getGender());
                assertEquals(FIRST_NAME, customer.getFirstName());
                assertEquals(LAST_NAME, customer.getLastName());
                assertEquals(MIDDLE_NAME, customer.getMiddleName());
                assertEquals(customerDate, customer.getBecomeCustomer());
            });
        }
    }

    @Nested
    class CustomerEqualsTest {
        private final String OTHER_FIRST_NAME = "John";
        private final String OTHER_LAST_NAME = "Doe";

        @Test
        void testDifferentCustomers() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();
            Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME).build();

            assertNotEquals(customer, otherCustomer);
        }

        @Test
        void testSameCustomer() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();
            Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();

            assertAll(() -> {
                assertEquals(customer, otherCustomer);
                assertNotSame(customer, otherCustomer);
            });
        }
    }

    @Nested
    class CustomerHashCodeTest {
        private final String OTHER_FIRST_NAME = "John";
        private final String OTHER_LAST_NAME = "Doe";

        @Test
        void testDifferentCustomers() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();
            Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME).build();

            assertNotEquals(customer.hashCode(), otherCustomer.hashCode());
        }

        @Test
        void testSameCustomer() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();
            Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME).build();

            assertEquals(customer.hashCode(), otherCustomer.hashCode());
        }
    }
}
