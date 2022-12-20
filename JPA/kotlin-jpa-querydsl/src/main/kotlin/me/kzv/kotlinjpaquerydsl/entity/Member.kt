package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.*

@Entity
class Member(
    @Id @GeneratedValue
    @Column(name = "member_id")
    val id: Long? = null,

    val name: String,

    @Embedded
    val address: Address, // embedded embeddable 둘 중 하나만 있어도 되긴함

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL]) // order 테이블의 member 를 바라보고 있다 (주인은 order)
    val orders: MutableList<Order> = mutableListOf(),

)