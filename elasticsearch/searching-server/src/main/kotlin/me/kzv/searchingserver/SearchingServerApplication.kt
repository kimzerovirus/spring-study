package me.kzv.searchingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SearchingServerApplication

fun main(args: Array<String>) {
    runApplication<SearchingServerApplication>(*args)
}
