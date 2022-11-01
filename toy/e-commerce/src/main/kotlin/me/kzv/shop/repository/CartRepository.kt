package me.kzv.shop.repository

import me.kzv.shop.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart, Long> {

    fun findByMemberId(memberId: Long): Cart
}