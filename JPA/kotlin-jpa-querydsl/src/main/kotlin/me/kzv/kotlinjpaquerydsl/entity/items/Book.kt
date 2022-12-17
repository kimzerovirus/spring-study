package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("book")
class Book(
    var author: String,
    var isbn: String,
    name: String,
    price: Int,
    stockQuantity: Int,
) : Item( name = name, price = price, stockQuantity = stockQuantity)
