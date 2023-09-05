package me.kzv.datajpa.entity;

import me.kzv.datajpa.repository.TestItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestItemTest {

    @Autowired
    TestItemRepository testItemRepository;

    @Test
    public void test() throws Exception {
        IntStream.rangeClosed(1,10).forEach(i -> {
            testItemRepository.save(new TestItem(Long.valueOf(i), "id"));
        });
        List<TestItem> oldAll = testItemRepository.findAll();
        System.out.println(oldAll);
    }
}