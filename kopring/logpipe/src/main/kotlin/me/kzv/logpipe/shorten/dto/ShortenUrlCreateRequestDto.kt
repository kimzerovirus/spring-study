package me.kzv.logpipe.shorten.dto

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL

data class ShortenUrlCreateRequestDto(
    @field:NotBlank(message = "url은 필수값입니다.")
    @field:URL(
        regexp = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)",
        message = "url 형식이 올바르지 않습니다."
    )
    val originalUrl: String,
)