package me.kzv.shop.repository

import me.kzv.shop.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem, Long> {
}