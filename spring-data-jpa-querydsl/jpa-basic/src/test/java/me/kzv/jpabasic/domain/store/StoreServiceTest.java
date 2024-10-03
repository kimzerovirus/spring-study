package me.kzv.jpabasic.domain.store;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 여기서 transactional 로 하나의 트랜잭션을 잡으니 insert 쿼리를 통해 생성된 데이터를 캐싱하는지
 * n + 1 쿼리가 생성되지 않음...
 */
@SpringBootTest
class StoreServiceTest {
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
    public void lazyLoading문제() throws Exception {
        long size = storeService.find();

        assertThat(size).isEqualTo(90000L);

        // 1. store 전체를 조회
        // 2. 조회된 store의 각 row를 기준으로 product 조회 - store가 3개이므로 product를 3번 조회 product p where p.store_id = ?
        // 3. 마찬가지로 employee를 3번 조회 employee e where e.store_id = ?
        // 총 6번의 n쿼리가 발생하여 총합 7번의 쿼리가 발생
    }

    @Test
    public void fetchJoin문제() throws Exception {
        assertThatThrownBy(() -> storeService.findByFetchJoin())
                .isInstanceOf(InvalidDataAccessApiUsageException.class)
                .hasMessage("org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags: [me.kzv.jpabasic.domain.store.Store.employees, me.kzv.jpabasic.domain.store.Store.products]");

        // 1:N 관계의 자식 테이블 여러곳에 Fetch Join을 사용하면 아래와 같이 에러가 발생한다.
        // JPA 에서 Fetch Join 의 조건은 다음과 같다.
        // - ToOne 은 몇개든 사용 가능합니다
        // - ToMany 는 1개만 가능합니다.

        // 이런 문제를 해결하는 방법 -> use batch size 사용
    }
}