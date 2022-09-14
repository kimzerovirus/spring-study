package me.kzv.blog.config;

import me.kzv.blog.web.Article
import me.kzv.blog.web.ArticleRepository
import me.kzv.blog.web.User
import me.kzv.blog.web.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(
        userRepository: UserRepository,
        articleRepository: ArticleRepository
    ) = ApplicationRunner {

        val smaldini = userRepository.save(
            User(
                login = "smaldini",
                firstname = "Stéphane",
                lastname = "Maldini"
            )
        )

        articleRepository.save(
            Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
            )
        )

        articleRepository.save(
            Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
            )
        )
    }
}