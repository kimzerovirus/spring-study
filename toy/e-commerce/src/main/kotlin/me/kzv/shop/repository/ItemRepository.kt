package me.kzv.shop.repository

import me.kzv.shop.entity.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>{
}