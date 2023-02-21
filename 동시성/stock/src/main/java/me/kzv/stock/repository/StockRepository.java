package me.kzv.stock.repository;

import jakarta.persistence.LockModeType;
import me.kzv.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

// https://skasha.tistory.com/49
public interface StockRepository extends JpaRepository<Stock, Long> {

    // 자원에 대한 동시 요청이 발생하여 일관성에 문제가 생길 것이라고 비관적으로 생각하고 이를 방지하기 위해 우선 락을 거는 방식
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithPessimisticLock(Long id);

    // 버전을 따로 두어서 버전에 맞지 않으면 업데이트 시키지 않는 방식, 다만 실패시 다시 실행시켜주는 로직이 필요함.
    // 실제로 락을 걸지 않고 작업을 하므로 충돌이 빈번하게 일어나지 않을 경우 성능상의 이점을 얻을 수 있으나 개발자가 실패시 재시도하는 과정을 구현해야한다.
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Stock s where s.id = :id")
    Stock findByIdWithOptimisticLock(Long id);
}
