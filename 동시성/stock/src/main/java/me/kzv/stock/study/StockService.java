package me.kzv.stock.study;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.kzv.stock.entity.Stock;
import me.kzv.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    // java synchronized 는 2대 이상의 서버에서 정합성을 보장하지 않음
    public synchronized void decrease(Long id, Long quantity) {
        // get stock
        Stock stock = stockRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        // 재고 감소
        stock.decrease(quantity);
        System.out.println("감소");

        // 저장
        stockRepository.saveAndFlush(stock);
    }
}
