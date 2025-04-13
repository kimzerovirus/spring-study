package me.kzv.logpipe.shorten

import me.kzv.logpipe.shorten.dto.ShortenUrlCreateRequestDto
import me.kzv.logpipe.shorten.dto.ShortenUrlCreateResponseDto
import me.kzv.logpipe.shorten.dto.ShortenUrlInformationDto
import me.kzv.logpipe.shorten.exception.LackOfShortenUrlKeyException
import me.kzv.logpipe.shorten.exception.NotFoundShortenUrlException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShortenUrlService (
    private val shortenUrlRepository: ShortenUrlRepository,
){
    @Transactional
    fun generateShortenUrl(shortenUrlCreateRequestDto: ShortenUrlCreateRequestDto): ShortenUrlCreateResponseDto {
        val shortenUrl = ShortenUrl.create(shortenUrlCreateRequestDto.originalUrl, getUniqueShortenUrlKey())
        shortenUrlRepository.save(shortenUrl)
        return ShortenUrlCreateResponseDto.from(shortenUrl)
    }

    @Transactional
    fun getOriginalUrlByShortenUrlKey(shortenUrlKey: String): String {
        val shortenUrl = shortenUrlRepository.findByShortenUrlKey(shortenUrlKey) ?: throw NotFoundShortenUrlException()
        shortenUrl.increaseRedirectCount()
        return shortenUrl.originalUrl
    }

    @Transactional(readOnly = true)
    fun getShortenUrlInformationByShortenUrlKey(shortenUrlKey: String): ShortenUrlInformationDto {
        val shortenUrl: ShortenUrl = shortenUrlRepository.findByShortenUrlKey(shortenUrlKey) ?: throw NotFoundShortenUrlException()
        return ShortenUrlInformationDto.from(shortenUrl)
    }

    private fun getUniqueShortenUrlKey(): String {
        val MAX_RETRY_COUNT = 5

        repeat(MAX_RETRY_COUNT) {
            val key = KeyGenerator.generate()
            shortenUrlRepository.findByShortenUrlKey(key) ?: return key
        }

        throw LackOfShortenUrlKeyException()
    }
}