package me.kzv.springtx.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTxTest {
    @Autowired
    Hello hello;

    @Test
    public void go() throws Exception {
        // 초기화 코드는 스프링이 초기화 시점에 호출
        // 초기화 코드가 먼저 호출되고, 그 다음에 트랜잭션 AOP 적용됨 - 순서가 꼬여서 적용 안된다
    }

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {
        @PostConstruct
        @Transactional
        public void initV1() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.warn(" ");
            log.info("hello init @PostConstructor tx active={}", txActive); // false
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.warn(" ");
            log.info("hello init @EventListener tx active={}", txActive); // true
        }
    }
}
