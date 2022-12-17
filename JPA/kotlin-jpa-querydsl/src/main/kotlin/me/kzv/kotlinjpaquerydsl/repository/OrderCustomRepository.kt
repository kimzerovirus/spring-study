package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.Order

interface OrderCustomRepository {
    fun findAllByString(orderSearch: OrderSearch) : List<Order>
}