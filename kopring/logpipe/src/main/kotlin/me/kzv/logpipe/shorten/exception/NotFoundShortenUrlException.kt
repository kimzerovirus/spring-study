package me.kzv.logpipe.shorten.exception

class NotFoundShortenUrlException(val shortenUrlKey: String) : RuntimeException()