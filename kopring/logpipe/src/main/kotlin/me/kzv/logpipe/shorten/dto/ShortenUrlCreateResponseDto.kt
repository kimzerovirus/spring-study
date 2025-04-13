package me.kzv.logpipe.shorten.dto

import me.kzv.logpipe.shorten.ShortenUrl

data class ShortenUrlCreateResponseDto(
    val originalUrl: String,
    val shortenUrlKey: String,
) {
    companion object {
        fun from(shortenUrl: ShortenUrl): ShortenUrlCreateResponseDto {
            with(shortenUrl) {
                return ShortenUrlCreateResponseDto(
                    originalUrl = originalUrl,
                    shortenUrlKey = shortenUrlKey,
                )
            }
        }
    }
}