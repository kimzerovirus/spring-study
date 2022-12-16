package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("C")
class Movie(
    var artist: String,
    var etc: String
) : Item()
