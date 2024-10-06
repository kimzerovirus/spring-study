package me.kzv.jpabasic.domain.strategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("GRAPE")
@Entity
public class Grape extends Fruit {
}
