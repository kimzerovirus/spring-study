package me.kzv.stock.service;

import me.kzv.stock.domain.Stock;
import me.kzv.stock.domain.StockRepository;
import me.kzv.stock.service.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class StockServiceTest {
    @Autowired StockService stockService;

    @Autowired StockRepository stockRepository;

    @BeforeEach
    public void before(){
        Stock stock = new Stock(1L, 100L);

        stockRepository.saveAndFlush(stock);
    }

    @Test
    public void stock_decrease() throws Exception {
        //given
        stockService.decrease(1L, 1L);

        //when
        Stock stock = stockRepository.findById(1L).orElseThrow();

        //then
        assertThat(stock.getQuantity()).isEqualTo(99);
    }


    /**
     * 동시에 접근하려다가 안됨 - 레이스 컨디션
     * 하나의 쓰레드가 작업을 완료하고 다음 쓰레드가 가져가서 작업을 해야하는데 동시에 접근을 해서 작업이 누락됨
     */
    @Test
    public void 동시에_100개의_요청() throws Exception {
        //given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        CountDownLatch latch = new CountDownLatch(threadCount);

        //when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decrease(1L, 1L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Stock stock = stockRepository.findById(1L).orElseThrow();

        //then
        assertThat(stock.getQuantity()).isEqualTo(0);
    }
}