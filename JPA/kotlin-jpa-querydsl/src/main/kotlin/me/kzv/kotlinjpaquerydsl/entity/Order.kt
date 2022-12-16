package me.kzv.kotlinjpaquerydsl.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.lang.IllegalStateException
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    @Column(name = "order_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 주인
    var member: Member,

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL]) // cascade order 만 저장하거나 삭제해도 orderItem 도 저장되고 삭제할 수 있음
    val orderItems: MutableList<OrderItem> = mutableListOf(),

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id") // order 또는 delivery 아무곳을 fk 로 잡아도 되지만 주문을 더 검색하므로
    var delivery: Delivery,

    val orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus, // 주문상태 [ORDER, CANCEL]
){
    //==양방향 연관관계를 위한 메서드==/
    // var 로 선언시 기본적으로 set메서드를 만들어주므로 jvm에서 만의 메서드명을 지정
    @JvmName("setMember1")
    fun setMember(member: Member){
        this.member = member
        member.orders.add(this)
    }
    fun addOrderItem(orderItem: OrderItem){
        orderItems.add(orderItem)
        orderItem.order = this
    }

    @JvmName("setDelivery1")
    fun setDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    //==생성 메서드==//
    companion object {
        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            val order = Order(member = member, delivery = delivery, status = OrderStatus.ORDER, orderDate = LocalDateTime.now())

            for (orderItem in orderItems) {
                order.addOrderItem(orderItem)
            }

            return order
        }
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    fun cancel(){
        if(delivery.status == DeliveryStatus.COMP){
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }
        status = OrderStatus.CANCEL

        // 하위 상품들도 다 취소
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }

    /**
     * 주문 조회
     */
    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (orderItem in orderItems) {
            totalPrice += orderItem.getTotalPrice()
        }

        return totalPrice
    }
}
