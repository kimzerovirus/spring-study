package me.kzv.logpipe.shorten.dto

import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.URL

data class ShortenUrlCreateRequestDto(
    @field:NotNull
    @field:URL(regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")
    val originalUrl: String,
)