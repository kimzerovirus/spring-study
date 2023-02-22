package me.kzv.simplesns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class SimpleSnsApplication

fun main(args: Array<String>) {
    runApplication<SimpleSnsApplication>(*args)
}
