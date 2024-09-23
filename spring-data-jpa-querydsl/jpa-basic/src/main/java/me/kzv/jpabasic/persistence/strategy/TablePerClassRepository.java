package me.kzv.jpabasic.persistence.strategy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TablePerClassRepository extends JpaRepository<TablePerClass, Long> {
}
