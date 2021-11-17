package me.kzv.ex.repository;

import me.kzv.ex.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kimzerovirus on 2021-11-17
 */


public interface MemoRepository extends JpaRepository<Memo, Long> {
}
