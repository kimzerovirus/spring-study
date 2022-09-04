package me.kzv.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // application.properties 설정은 안되지만 이렇게 어노테이션으로 설정하니깐 되는듯 - Before, After 를 사용하려면 static 으로 만들어야되는데 이때 코틀린은 이러한 설정을 해줘야한다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

//  With this configuration, we can now use @BeforeAll and @AfterAll annotations on regular methods like shown in updated version of IntegrationTests above.
    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> Assert article page title, content and status code")
        val title = "Reactor Aluminium has landed"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem ipsum", "dolor sit amet")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}

// https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testcontext-junit-jupiter-di

/**
 * JUnit 5 now used by default in Spring Boot provides various features very handy with Kotlin,
 * including autowiring of constructor/method parameters which allows to use non-nullable val properties and the possibility to use @BeforeAll/@AfterAll on regular non-static methods.
 *
 * Writing JUnit 5 tests in Kotlin
 * For the sake of this example, let’s create an integration test in order to demonstrate various features:
 * - We use real sentences between backticks instead of camel-case to provide expressive test function names
 * - JUnit 5 allows to inject constructor and method parameters, which is a good fit with Kotlin read-only and non-nullable properties
 * - This code leverages getForObject and getForEntity Kotlin extensions (you need to import them)
 */