package me.kzv.jpabasic.domain.strategy.singletable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
