package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.*
import me.kzv.kotlinjpaquerydsl.entity.items.Item

@Entity
class OrderItem(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null, // orderItem 이 먼저 생성된 후 order 가 생성되므로 일단은 null

    val orderPrice: Int,
    val count: Int,
) {
    //==생성 메서드==//
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(item = item, orderPrice = orderPrice, count = count)
            item.removeStock(count)
            return orderItem
        }
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소 - 재고 수량 원복
     */
    fun cancel() {
        item.addStock(count)
    }

    /**
     * 주문상품 전체 가격 조회
     */
    fun getTotalPrice(): Int = orderPrice * count

}
