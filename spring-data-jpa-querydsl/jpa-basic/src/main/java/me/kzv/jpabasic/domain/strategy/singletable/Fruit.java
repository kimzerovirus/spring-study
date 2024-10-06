package me.kzv.jpabasic.domain.strategy.singletable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import me.kzv.jpabasic.common.BaseEntity;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "name")
@Entity
abstract public class Fruit extends BaseEntity {
}
