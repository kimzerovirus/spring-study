package com.hyeran.study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class StudyApplication

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}
