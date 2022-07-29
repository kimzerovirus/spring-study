package me.kzv.shopapi.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("M") // 싱글테이블 전략에서 구분자
public class Movie extends Item{

    private String artist;
    private String etc;
}
