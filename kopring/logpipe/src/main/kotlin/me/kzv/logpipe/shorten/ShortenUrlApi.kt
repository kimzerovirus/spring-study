package me.kzv.logpipe.shorten

import jakarta.validation.Valid
import me.kzv.logpipe.shorten.dto.ShortenUrlCreateRequestDto
import me.kzv.logpipe.shorten.dto.ShortenUrlCreateResponseDto
import me.kzv.logpipe.shorten.dto.ShortenUrlInformationDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URISyntaxException


@RestController
class ShortenUrlApi (
    private val shortenUrlService: ShortenUrlService,
){
   @PostMapping("/shortenUrl")
    fun createShortenUrl(
       @RequestBody shortenUrlCreateRequestDto: @Valid ShortenUrlCreateRequestDto
    ): ResponseEntity<ShortenUrlCreateResponseDto> {
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

    @GetMapping("/shortenUrl/{shortenUrlKey}")
    fun getShortenUrlInformation(
        @PathVariable shortenUrlKey: String
    ): ResponseEntity<ShortenUrlInformationDto> {
        return ResponseEntity.ok(shortenUrlService.getShortenUrlInformationByShortenUrlKey(shortenUrlKey))
    }
}