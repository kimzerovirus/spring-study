package me.kzv.jpabasic.domain.store;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * spring:
 *   jpa:
 *     properties:
 *       hibernate.default_batch_fetch_size: 1000
 */
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000") // 옵션 적용
@SpringBootTest
public class UseBatchSizeStoreServiceTest {
    @Autowired
    StoreService storeService;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
        Store store1 = new Store("서점", "서울시 강남구");
        store1.addProduct(new Product("책1_1", 10000L));
        store1.addProduct(new Product("책1_2", 20000L));
        store1.addEmployee(new Employee("직원1", LocalDate.now()));
        store1.addEmployee(new Employee("직원2", LocalDate.now()));
        storeRepository.save(store1);

        Store store2 = new Store("서점2", "서울시 강남구");
        store2.addProduct(new Product("책2_1", 10000L));
        store2.addProduct(new Product("책2_2", 20000L));
        store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
        store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
        storeRepository.save(store2);

        Store store3 = new Store("서점3", "서울시 강남구");
        store3.addProduct(new Product("책3_1", 10000L));
        store3.addProduct(new Product("책3_2", 20000L));
        store3.addEmployee(new Employee("직원3_1", LocalDate.now()));
        store3.addEmployee(new Employee("직원3_2", LocalDate.now()));
        storeRepository.save(store3);

        System.out.println("=======================================================");
        System.out.println("===================== 조회 쿼리 시작 =====================");
        System.out.println("=======================================================");
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.out.println("=======================================================");
        System.out.println("========================= END =========================");
        System.out.println("=======================================================");
        storeRepository.deleteAll();
    }

    @Test
    public void BatchSize옵션_사용() throws Exception {
        long size = storeService.find();

        assertThat(size).isEqualTo(90000L);

        // 1. store 조회
        // 2. in절에 조회된 store id를 넣고 product 와 employee 를 조회한다.
        // 3. 총 3번의 쿼리 조회 - store, product + in, employee + in
    }

    @Test
    public void batch_size_물음표가_몇개야() {
        var str = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        System.out.println(str.split(",").length);
    }
}