package me.kzv.kotlinjpaquerydsl.service

import jakarta.persistence.EntityNotFoundException
import me.kzv.kotlinjpaquerydsl.entity.Address
import me.kzv.kotlinjpaquerydsl.entity.Member
import me.kzv.kotlinjpaquerydsl.entity.OrderStatus
import me.kzv.kotlinjpaquerydsl.entity.items.Book
import me.kzv.kotlinjpaquerydsl.exception.NotEnoughStockException
import me.kzv.kotlinjpaquerydsl.repository.OrderRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@Rollback(true)
@Transactional
@SpringBootTest
class OrderServiceTest {
    @Autowired lateinit var orderService: OrderService
    @Autowired lateinit var orderRepository: OrderRepository
    @Autowired lateinit var memberService: MemberService
    @Autowired lateinit var itemService: ItemService

    @Test
    fun `상품 주문`() {
        //given
        val member = createMember()
        val book = createBook()

        val orderCount = 10
        //when
        val orderId = orderService.order(memberId = member.id!!, itemId = book.id!!, orderCount)

        //then
        val getOrder = orderRepository.findByIdOrNull(orderId)

        // expected | actual | message
        assertEquals(OrderStatus.ORDER, getOrder?.status, "상품 주문시 상태는 ORDER")
        assertEquals(1, getOrder?.orderItems?.size, "주문한 상품의 종류 수")
        assertEquals(10000 * orderCount, getOrder?.getTotalPrice(), "주문 가격은 가격 * 수량")
        assertEquals(90, book.stockQuantity, "주문 수량 만큼 재고 수량이 감소한다")
    }

    @Test
    fun `상품 주문 재고 수량 초과`() {
        //given
        val member = createMember()
        val item = createBook()
        val orderCount = Int.MAX_VALUE

        //when

        //then
        val exception = assertThrows(NotEnoughStockException::class.java){orderService.order( memberId = member.id!!, itemId = item.id!!, count = orderCount) }
        assertEquals("재고 수량이 부족합니다", exception.message)
    }
    
    @Test
    fun `주문 취소`() {
        //given
        val member = createMember()
        val item = createBook()

        val orderId = orderService.order(memberId = member.id!!, itemId = item.id!!, count = 2)

        //when
        orderService.cancelOrder(orderId)

        //then
        val getOrder =
            orderRepository.findByIdOrNull(orderId) // ?: throw EntityNotFoundException() 이거 안해주면 safe call (?) 해줘야 함 아니면 null safety (!!)
        assertEquals(OrderStatus.CANCEL, getOrder?.status, "주문 취소시 상태는 CANCEL")
        assertEquals(100, item.stockQuantity, "주문이 취소된 상품은 재고가 원상 복구가 되어야 한다")
    }

    private fun createMember(): Member {
        val member = Member(name = "테스트 회원1", address = Address("서울", "서부샛길", "123-123"))
        memberService.join(member)

        return member
    }

    private fun createBook(): Book {
        val book = Book(name = "JPA with Kotlin", price = 10000, stockQuantity = 100, author = "kzv", isbn = "abc123453678")
        itemService.saveItem(book)

        return book
    }
}