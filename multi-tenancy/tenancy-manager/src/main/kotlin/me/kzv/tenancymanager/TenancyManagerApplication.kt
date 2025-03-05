package me.kzv.tenancymanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

//@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@SpringBootApplication
class TenancyManagerApplication

fun main(args: Array<String>) {
    runApplication<TenancyManagerApplication>(*args)
}
