package me.kzv.shop.repository

import me.kzv.shop.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.query.Param

interface ItemRepository : JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
    fun findByItemName(itemName: String): List<Item>
    fun findByItemNameOrItemDetail(itemName: String, itemDetail: String): List<Item>
    fun findByPriceLessThan(price: Int): List<Item>
    fun findByPriceLessThanOrderByPriceDesc(price: Int): List<Item>

    @Query(
        "select i from Item i where i.itemDetail like " +
                "%:itemDetail% order by i.price desc"
    )
    fun findByItemDetail(@Param("itemDetail") itemDetail: String): List<Item>

    @Query(
        value = "select * from item i where i.item_detail like " +
                "%:itemDetail% order by i.price desc", nativeQuery = true
    )
    fun findByItemDetailByNative(@Param("itemDetail") itemDetail: String): List<Item>
}