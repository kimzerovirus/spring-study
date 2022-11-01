package me.kzv.shop.repository

import me.kzv.shop.entity.ItemImg
import org.springframework.data.jpa.repository.JpaRepository

interface ItemImgRepository : JpaRepository<ItemImg, Long> {

    fun findByItemIdOrderByIdAsc(itemId: Long): List<ItemImg>

    fun findByItemIdAndMainImgYn(itemId: Long, mainImgYn: String)
}