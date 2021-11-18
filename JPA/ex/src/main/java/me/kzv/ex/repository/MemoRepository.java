package me.kzv.ex.repository;

import me.kzv.ex.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kimzerovirus on 2021-11-17
 */

public interface MemoRepository extends JpaRepository<Memo, Long> {

	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

	void deleteMemoByMnoLessThan(long num);
}
