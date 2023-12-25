package me.kzv.shop.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import me.kzv.shop.entity.constant.ItemSellStatus

class ItemRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
)
//{
//    private fun searchSellStatusEq(searchSellStatus: ItemSellStatus): BooleanExpression? =
//        if (searchSellStatus == null) {
//            null
//        } else {
//            QItem.item.itesSellStatus.eq(searchSellStatus)
//