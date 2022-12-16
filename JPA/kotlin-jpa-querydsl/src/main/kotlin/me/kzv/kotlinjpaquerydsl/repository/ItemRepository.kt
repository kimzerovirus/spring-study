package me.kzv.kotlinjpaquerydsl.repository

import me.kzv.kotlinjpaquerydsl.entity.items.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long> {
}