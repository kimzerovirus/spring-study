package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.OrderStatus

data class OrderSearch(
    val memberName: String,
    val orderStatus: OrderStatus,
)
