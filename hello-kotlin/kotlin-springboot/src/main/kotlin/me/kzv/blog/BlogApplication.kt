package me.kzv.blog

import me.kzv.blog.config.BlogProperties
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BlogProperties::class)
@SpringBootApplication
class BlogApplication

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args){
        setBannerMode(Banner.Mode.OFF) // 스프링부트 배너 숨기기
    }
}


/**
 * Compared to Java, you can notice the lack of semicolons,
 * the lack of brackets on empty class (you can add some if you need to declare beans via @Bean annotation)
 * and the use of runApplication top level function.
 * runApplication<BlogApplication>(*args) is Kotlin idiomatic alternative to SpringApplication.run(BlogApplication::class.java, *args)
 * and can be used to customize the application with following syntax.
 */