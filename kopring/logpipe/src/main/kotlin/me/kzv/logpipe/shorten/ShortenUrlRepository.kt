package me.kzv.logpipe.shorten

import org.springframework.data.jpa.repository.JpaRepository

interface ShortenUrlRepository : JpaRepository<ShortenUrl, Long> {
    fun findByShortenUrlKey(shortenUrlKey: String): ShortenUrl?
}