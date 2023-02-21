package me.kzv.stock.repository;

import me.kzv.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Lock 이 걸리기 때문에 별도의 쓰레드 풀을 이용하는게 옳다
// 트랜잭션과 세션 관리에 유의해야한다.
public interface LockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);
}
