package me.kzv.jpabasic.domain.strategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("APPLE")
@Entity
public class Apple extends Fruit {
}
