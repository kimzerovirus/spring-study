package me.kzv.jpabasic.persistence.strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@NoArgsConstructor
@Getter
@Table(name = "TABLE_PER_CLASS_2")
@Entity
public class TablePer2 extends TablePerClass {
    private String description;
}
