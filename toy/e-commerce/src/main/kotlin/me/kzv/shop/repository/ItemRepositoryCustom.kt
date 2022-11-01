package me.kzv.shop.repository

import me.kzv.shop.controller.dto.ItemSearchDto
import me.kzv.shop.controller.dto.MainItemDto
import me.kzv.shop.entity.Item
import org.springframework.data.domain.Page
import java.awt.print.Pageable

interface ItemRepositoryCustom {

    fun getAdminItemPage(itemSearchDto: ItemSearchDto, pageable: Pageable): Page<Item>

    fun getMainItemPage(itemSearchDto: ItemSearchDto, pageable: Pageable): Page<MainItemDto>
}