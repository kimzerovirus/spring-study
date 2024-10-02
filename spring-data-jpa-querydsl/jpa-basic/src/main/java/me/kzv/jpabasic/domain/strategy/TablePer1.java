package me.kzv.jpabasic.domain.strategy;

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
@Table(name = "TABLE_PER_CLASS_1")
@Entity
public class TablePer1 extends TablePerClass {
    private String title;
}
