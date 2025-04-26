package core.tags;

import me.kzv.core.tags.Customer;
import me.kzv.core.tags.CustomersRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 일종의 카테고리화 하기 위한 태그인듯
// 크게 필요는 없을듯

@Tag("repository")
public class CustomersRepositoryTest {
    private final String CUSTOMER_NAME = "John Smith";
    private final CustomersRepository repository = new CustomersRepository();

    @Test
    void testNonExistence() {
        boolean exists = repository.contains("John Smith");

        assertFalse(exists);
    }

    @Test
    void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));

        assertTrue(repository.contains("John Smith"));
    }
}
