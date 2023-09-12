package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TestCustomRepositoryTest {
    @Autowired
    TestCustomRepository testCustomRepository;

    @Autowired
    TestRepository testRepository;

    @Test
    public void test() throws Exception {
        testRepository.save(new TestEntity(null, LocalDate.now()));

        List<TestEntity> byDate = testCustomRepository.getByDate();
        for (TestEntity testEntity : byDate) {
            System.out.println(testEntity);
        }
    }
}