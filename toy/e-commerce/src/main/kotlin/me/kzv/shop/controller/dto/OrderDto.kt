package me.kzv.shop.controller.dto

import me.kzv.shop.entity.Order
import me.kzv.shop.entity.OrderItem
import me.kzv.shop.entity.constant.OrderStatus
import java.time.format.DateTimeFormatter
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull


data class OrderDto(
    @field:NotNull(message = "상품 아이디는 필수 입력 값입니다.")
     val itemId: Long,

    @field:Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @field:Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
     val count: Int = 0
)

data class OrderHistDto(
    val order: Order
) {
     val orderId //주문아이디
            : Long = order.id!!
     val orderDate //주문날짜
            : String = order.orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
     val orderStatus //주문 상태
            : OrderStatus = order.orderStatus
     private val orderItemDtoList: MutableList<OrderItemDto> = ArrayList<OrderItemDto>()

    //주문 상품리스트
    fun addOrderItemDto(orderItemDto: OrderItemDto) {
        orderItemDtoList.add(orderItemDto)
    }
}

data class OrderItemDto(
    val orderItem: OrderItem,
    val img: String
) {
     val itemNm //상품명
            : String = orderItem.item.itemName
     val count //주문 수량
            : Int = orderItem.count
     val orderPrice //주문 금액
            : Int = orderItem.orderPrice
     val imgUrl //상품 이미지 경로
            : String = img
}