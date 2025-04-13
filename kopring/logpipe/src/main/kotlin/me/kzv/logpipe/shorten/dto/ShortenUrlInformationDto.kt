package me.kzv.logpipe.shorten.dto

import me.kzv.logpipe.shorten.ShortenUrl

data class ShortenUrlInformationDto(
    val originalUrl: String,
    val shortenUrlKey: String,
    val redirectCount: Long,
) {
    companion object {
        fun from(shortenUrl: ShortenUrl): ShortenUrlInformationDto {
            with(shortenUrl) {
                return ShortenUrlInformationDto(
                    originalUrl = originalUrl,
                    shortenUrlKey = shortenUrlKey,
                    redirectCount = redirectCount
                )
            }
        }
    }
}