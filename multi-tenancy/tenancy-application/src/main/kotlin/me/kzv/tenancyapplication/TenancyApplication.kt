package me.kzv.tenancyapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TenancyApplication

fun main(args: Array<String>) {
    runApplication<TenancyApplication>(*args)
}
