package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    val author: String,
    val isbn: String
) : Item()
