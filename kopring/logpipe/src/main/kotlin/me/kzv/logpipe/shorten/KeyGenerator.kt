package me.kzv.logpipe.shorten

import kotlin.random.Random

class KeyGenerator {
    companion object {
        private const val BASE_CHARACTERS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz"

        fun generate(): String {
            var key = ""

            repeat(8) {
                key += BASE_CHARACTERS[Random.nextInt(BASE_CHARACTERS.length)]
            }

            return key
        }
    }
}