package me.kzv.kotlinjpaquerydsl.service

import jakarta.persistence.EntityNotFoundException
import me.kzv.kotlinjpaquerydsl.entity.*
import me.kzv.kotlinjpaquerydsl.entity.items.Item
import me.kzv.kotlinjpaquerydsl.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderCustomRepository: OrderCustomRepositoryImpl,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
) {
    /**
     * 주문
     */
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        // 엔티티 조회
        val member: Member = memberRepository.findByIdOrNull(memberId) ?: throw EntityNotFoundException()
        val item: Item = itemRepository.findByIdOrNull(itemId) ?: throw EntityNotFoundException()

        // 배송정보 생성
        val delivery = Delivery(address = member.address, status = DeliveryStatus.READY)

        // 주문상품 생성
        val orderItem = OrderItem.createOrderItem(item, item.price, count)

        // 주문 생성
        val order: Order = Order.createOrder(member, delivery, orderItem)

        // 주문 저장
        orderRepository.save(order) // <- cascade 옵션으로 인해 order 가 저장될때 orderItem 과 delivery 까지 저장된다.

        return order.id!!
    }

    /**
     * 취소
     */
    @Transactional
    fun cancelOrder(orderId: Long) {
        // 주문 엔티티 조회
        val order: Order = orderRepository.findByIdOrNull(orderId) ?: throw EntityNotFoundException()
        // 주문 취소
        order.cancel()
    }

    /**
     * 검색
     */
    fun findAll(): List<Order> {
        return orderRepository.findAll()
    }

    fun findOrders(orderSearch: OrderSearch): List<Order> {
        return orderCustomRepository.findAllByString(orderSearch)
    }
}