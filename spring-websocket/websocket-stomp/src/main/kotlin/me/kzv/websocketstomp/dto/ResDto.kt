package me.kzv.websocketstomp.dto

import java.time.LocalDateTime

data class ResDto (
    val message: String,
    val localDateTime: LocalDateTime,
)