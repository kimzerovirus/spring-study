package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.*

@Entity
@DiscriminatorValue("A") // 싱글테이블 전략에서 구분자
class Album : Item()