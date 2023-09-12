package me.kzv.helloquerydsl.repository;

import me.kzv.helloquerydsl.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
