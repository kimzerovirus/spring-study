package me.kzv.jpabasic.domain.strategy.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DiscriminatorValue("GRAPE")
@Entity
public class Grape extends Fruit {
}
