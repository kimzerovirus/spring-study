package me.kzv.shop.repository

import me.kzv.shop.controller.dto.CartDetailDto
import me.kzv.shop.entity.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CartItemRepository : JpaRepository<CartItem, Long> {

    // kotlin 에서 optional => ~OrNull 또는 리턴타입을 ? nullable 하게
    fun findByCartIdAndItemId(cartId: Long, itemId: Long): CartItem

    @Query(
        "select new me.kzv.shop.controller.dto.CartDetailDto(ci.id, i.itemName, i.price, ci.count, im.imgUrl) " +
                "from CartItem ci, ItemImg im " +
                "join ci.item i " +
                "where ci.cart.id = :cartId " +
                "and im.item.id = ci.item.id " +
                "and im.mainImgYn = 'Y' " +
                "order by ci.createdDate desc"
    )
    fun findCartDetailDtoList(cartId: Long): List<CartDetailDto>

}