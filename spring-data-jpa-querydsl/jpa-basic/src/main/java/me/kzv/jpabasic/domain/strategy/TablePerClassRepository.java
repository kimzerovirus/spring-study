package me.kzv.jpabasic.domain.strategy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TablePerClassRepository extends JpaRepository<TablePerClass, Long> {
}
