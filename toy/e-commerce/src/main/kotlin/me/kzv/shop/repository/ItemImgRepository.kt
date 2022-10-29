package me.kzv.shop.repository

import me.kzv.shop.entity.ItemImg
import org.springframework.data.jpa.repository.JpaRepository

interface ItemImgRepository : JpaRepository<ItemImg, Long> {
}