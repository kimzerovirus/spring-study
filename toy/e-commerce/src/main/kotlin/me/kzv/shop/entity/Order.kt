package me.kzv.shop.entity

import me.kzv.shop.entity.constant.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Order(
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member,

    var orderDate: LocalDateTime, // 주문일

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus, // 주문 상태

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    val orderItems: MutableList<OrderItem> = mutableListOf(),

    ) : BaseEntity() {

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.groupOrder(this)
    }

    fun getTotalPrice(): Int {
        var totalPrice = 0;
        for (orderItem in orderItems) {
            totalPrice += orderItem.getTotalPrice()
        }

        return totalPrice
    }

    fun cancelOrder(){
        orderStatus = OrderStatus.CANCEL
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }
}

fun createOrder(member: Member, orderItemList: MutableList<OrderItem>): Order {
    val order = Order(member = member, orderStatus = OrderStatus.ORDER, orderDate = LocalDateTime.now())
    for (orderItem in orderItemList) {
        order.addOrderItem(orderItem)
    }

    return order
}