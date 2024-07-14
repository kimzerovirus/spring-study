package me.kzv.multitenancy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MultiTenancyApplication

fun main(args: Array<String>) {
    runApplication<MultiTenancyApplication>(*args)
}
