package me.kzv.datajpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class TestItem {
    @Id
    private Long id;

    private String name;
}
