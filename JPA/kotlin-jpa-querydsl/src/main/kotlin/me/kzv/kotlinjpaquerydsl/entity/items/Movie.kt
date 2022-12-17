package me.kzv.kotlinjpaquerydsl.entity.items

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("movie")
class Movie(
    var artist: String,
    var etc: String
) : Item()
