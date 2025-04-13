package me.kzv.logpipe.shorten

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(uniqueConstraints = [
    UniqueConstraint(name = "uq_shorten_url_key", columnNames = ["shorten_url_key"])
])
class ShortenUrl private constructor(
    @Id
    val id: Long = 0,
    @Column(length = MAX_URL_LENGTH)
    val originalUrl: String,
    var shortenUrlKey: String,
    var redirectCount: Long = 0,
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
){

    fun increaseRedirectCount() {
        this.redirectCount += 1
    }

    companion object {
        private const val MAX_URL_LENGTH = 2000

        fun create( originalUrl: String, shortenUrlKey: String) : ShortenUrl {
            return ShortenUrl(originalUrl = originalUrl, shortenUrlKey = shortenUrlKey)
        }
    }
}