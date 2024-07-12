package me.kzv.minio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MinioStorageServiceApplication

fun main(args: Array<String>) {
    runApplication<MinioStorageServiceApplication>(*args)
}
