package me.kzv.shop.entity

import javax.persistence.*

@Entity
class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    var cart: Cart,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    var count: Int,

    ) : BaseEntity() {

    fun addCart(count: Int) {
        this.count += count
    }

    fun updateCount(count: Int) {
        this.count = count
    }
}

fun createCartItem(cart: Cart, item: Item, count: Int): CartItem = CartItem(cart = cart, item = item, count = count)
