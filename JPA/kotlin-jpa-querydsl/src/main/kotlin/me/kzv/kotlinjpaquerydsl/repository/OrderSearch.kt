package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.OrderStatus

data class OrderSearch(
    var memberName: String?,
    var orderStatus: OrderStatus?,
)
