package me.kzv.helloquerydsl.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Hello {

    @Id @GeneratedValue
    private Long id;

    private String hello;
}
