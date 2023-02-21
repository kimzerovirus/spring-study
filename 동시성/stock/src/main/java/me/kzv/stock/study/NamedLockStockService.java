package me.kzv.stock.study;

import lombok.RequiredArgsConstructor;
import me.kzv.stock.entity.Stock;
import me.kzv.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NamedLockStockService {
    /**
     *  이름을 가진 락(이름 메타데이터)을 획든한 후 락이 해제 될때까지 다른 세션은 이 락을 획득할 수 없음
     *  Transaction 이 해제 될때 자동으로 락이 해제되지 않으므로 별도의 명령으로 해제해야한다.
     *
     *  Pessimistic Lock 이 Stock 데이터에 Lock 을 걸었다면 Named Lock 은 별도의 공간에 Lock 을 걸어 접근을 제한한다.
     * */
    private final StockRepository stockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
