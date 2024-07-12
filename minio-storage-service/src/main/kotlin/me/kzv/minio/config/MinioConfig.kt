package me.kzv.minio.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MinioConfig {
    @Value("\${minio.url}")
    private val url: String? = null

    @Value("\${minio.access.name}")
    private lateinit var accessKey: String

    @Value("\${minio.access.secret}")
    private lateinit var accessSecret: String

    @Bean
    fun minioClient(): MinioClient {
        return MinioClient.builder()
            .endpoint(url)
            .credentials(accessKey, accessSecret)
            .build()
    }
}
