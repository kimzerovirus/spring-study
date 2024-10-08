package me.kzv.jpabasic.domain.strategy;

import jakarta.transaction.Transactional;
import me.kzv.jpabasic.domain.strategy.taberperclass.TablePer1;
import me.kzv.jpabasic.domain.strategy.taberperclass.TablePer2;
import me.kzv.jpabasic.domain.strategy.taberperclass.TablePerClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 테이블 상속 전략 - TABLE PER CLASS
 */
@Transactional
@SpringBootTest
class TablePerClassRepositoryTest {

    @Autowired
    TablePerClassRepository tablePerClassRepository;

    @BeforeEach
    void setUp() {
        tablePerClassRepository.save(TablePer1.builder().title("title").build());
        tablePerClassRepository.save(TablePer2.builder().description("description").build());
    }

    @Test
    public void testTablePerClass() {
        tablePerClassRepository.findAll().forEach(System.out::println);
    }
}