package me.kzv.logpipe.shorten

import jakarta.validation.Valid
import me.kzv.logpipe.shorten.dto.ShortenUrlCreateRequestDto
import me.kzv.logpipe.shorten.dto.ShortenUrlCreateResponseDto
import me.kzv.logpipe.shorten.dto.ShortenUrlInformationDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
class ShortenUrlApi (
    private val shortenUrlService: ShortenUrlService,
){
   private val logger = LoggerFactory.getLogger(ShortenUrlApi::class.java)

   @PostMapping("/shorten-url")
    fun createShortenUrl(
       @RequestBody shortenUrlCreateRequestDto: @Valid ShortenUrlCreateRequestDto
    ): ResponseEntity<ShortenUrlCreateResponseDto> {
        logger.trace("createShortenUrl: {}", shortenUrlCreateRequestDto)
        return ResponseEntity.ok(shortenUrlService.generateShortenUrl(shortenUrlCreateRequestDto))
    }

    @GetMapping("/{shortenUrlKey}")
    fun redirectShortenUrl(
        @PathVariable shortenUrlKey: String
    ): ResponseEntity<*> {
        val originalUrl = shortenUrlService.getOriginalUrlByShortenUrlKey(shortenUrlKey)
        val httpHeaders = HttpHeaders()
        httpHeaders.location = URI(originalUrl)
        return ResponseEntity<Any>(httpHeaders, HttpStatus.MOVED_PERMANENTLY)
    }

    @GetMapping("/shorten-url/{shortenUrlKey}")
    fun getShortenUrlInformation(
        @PathVariable shortenUrlKey: String
    ): ResponseEntity<ShortenUrlInformationDto> {
        return ResponseEntity.ok(shortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey))
    }
}