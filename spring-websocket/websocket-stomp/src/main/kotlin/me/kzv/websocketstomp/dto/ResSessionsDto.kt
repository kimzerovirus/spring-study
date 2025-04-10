package me.kzv.websocketstomp.dto

import java.time.LocalDateTime

data class ResSessionsDto (
    val count: Int,
    val sessions: List<String>,
    val sourceSessionId: String,
    val localDateTime: LocalDateTime,
)