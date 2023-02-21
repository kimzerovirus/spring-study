package me.kzv.stock.facade;

import lombok.RequiredArgsConstructor;
import me.kzv.stock.repository.LockRepository;
import me.kzv.stock.study.NamedLockStockService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NamedLockStockFacade {

    private final LockRepository lockRepository;
    private final NamedLockStockService namedLockStockService;

    @Transactional
    public void decrease(Long id, Long quantity) {
        try{
            lockRepository.getLock(id.toString());
            namedLockStockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
