package me.kzv.shop.entity

import javax.persistence.*

@Entity
class OrderItem(

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    var id: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null, // 일단 OrderItem 을 생성하고 그 다음에 groupOrder 메소드를 통해 그룹핑이 된다. -> TODO 처음 생성 단계에서 Order 를 같이 넣는 것으로 변경하기

    var orderPrice: Int,

    var count: Int,

):BaseEntity(){

    fun getTotalPrice(): Int = orderPrice * count

    fun cancel(){
        item.addStock(count)
    }

    fun groupOrder(order: Order){
        this.order = order
    }
}

fun createOrderItem(item: Item, count: Int): OrderItem {
    val order = OrderItem(item = item, count = count, orderPrice = item.price)
    item.removeStock(count)

    return order
}
