package me.kzv.jpabasic.domain.strategy.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.jpabasic.common.BaseEntity;

@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Animal extends BaseEntity {
    private String name;
}
