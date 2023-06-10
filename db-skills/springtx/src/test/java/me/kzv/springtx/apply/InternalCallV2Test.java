package me.kzv.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV2Test {

    @Autowired
    CallService callService;

    @Test
    public void InternalCallV1TestConfig() throws Exception {
        callService.external();
    }

    @TestConfiguration
    static class CallConfig {
        @Bean
        CallService callService() {
            return new CallService();
        }

        @Bean
        InternalService internalService(){
            return new InternalService();
        }
    }


    @Slf4j
    static class CallService {
        @Autowired
        InternalService internalService;

        public void external() {
            log.info("call external");
            printTxInfo();
            internalService.internal(); // 별도의 클래스로 감싸면 트랜잭션이 잘 적용됨
            // 이전에는 프록시가 안걸리 CallService 자신이 먼저 호출되면서 내부의 트랜잭션 프록시가 사용되지 않았지만
            // 호출시점에 이미 프록시로 감싸지 internal 메서드가 있으므로 상관이 없게 되어 트랜잭션이 잘 적용됨
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }
    }

    static class InternalService{
        @Transactional
        public void internal() {
            log.info("call internal");
            printTxInfo();
        }

        private void printTxInfo() {
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }
    }
}
