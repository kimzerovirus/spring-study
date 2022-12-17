package me.kzv.kotlinjpaquerydsl.controller

import me.kzv.kotlinjpaquerydsl.entity.Member
import me.kzv.kotlinjpaquerydsl.entity.Order
import me.kzv.kotlinjpaquerydsl.entity.items.Item
import me.kzv.kotlinjpaquerydsl.repository.OrderSearch
import me.kzv.kotlinjpaquerydsl.service.ItemService
import me.kzv.kotlinjpaquerydsl.service.MemberService
import me.kzv.kotlinjpaquerydsl.service.OrderService
import me.kzv.kotlinjpaquerydsl.utils.logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class OrderController(
    private val memberService: MemberService,
    private val orderService: OrderService,
    private val itemService: ItemService,
) {
    @GetMapping("/order")
    fun createForm(model: Model): String {
        val members: List<Member> = memberService.findMembers()
        val items: List<Item> = itemService.findItems()

        model.addAttribute("members", members)
        model.addAttribute("items", items)

        return "order/orderForm"
    }

    @PostMapping("/order")
    fun order(
        @RequestParam("memberId") memberId: Long,
        @RequestParam("itemId") itemId: Long,
        @RequestParam("count") count: Int
    ): String {
        orderService.order(memberId, itemId, count)

        return "redirect:/orders"
    }

    @GetMapping("/orders")
    fun orderList(@ModelAttribute("orderSearch") orderSearch: OrderSearch, model: Model): String {
        val orders: List<Order> = orderService.findOrders(orderSearch)
        model.addAttribute("orders", orders)

        return "order/orderList"
    }

    @PostMapping("/orders/{orderId}/cancel")
    fun cancelOrder(@PathVariable("orderId") orderId: Long): String {
        orderService.cancelOrder(orderId)

        return "redirect:/orders"
    }
}