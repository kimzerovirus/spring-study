package me.kzv.datajpa.repository;

import me.kzv.datajpa.entity.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestItemRepository extends JpaRepository<TestItem, Long> {
}
