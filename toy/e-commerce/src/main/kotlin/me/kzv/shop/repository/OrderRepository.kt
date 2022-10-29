package me.kzv.shop.repository

import me.kzv.shop.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {

}