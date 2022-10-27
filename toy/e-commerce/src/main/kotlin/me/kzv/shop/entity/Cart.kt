package me.kzv.shop.entity

import javax.persistence.*

@Entity
class Cart(

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    var member: Member,

    ) : BaseEntity()

fun createCart(member: Member): Cart = Cart(member = member)