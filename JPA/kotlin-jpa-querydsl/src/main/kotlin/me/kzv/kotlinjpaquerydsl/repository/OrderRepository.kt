package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}