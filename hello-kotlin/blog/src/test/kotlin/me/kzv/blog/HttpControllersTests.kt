package me.kzv.blog

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var userRepository: UserRepository

    @MockkBean
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val juergen = User(login = "springjuergen", firstname = "Juergen", lastname =  "Hoeller")
        val spring5Article = Article(title = "Spring Framework 5.0 goes GA", headline = "Dear Spring community ...", content = "Lorem ipsum", author = juergen)
        val spring43Article = Article(title = "Spring Framework 4.3 goes GA", headline = "Dear Spring community ...",  content = "Lorem ipsum", author = juergen)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns listOf(spring5Article, spring43Article)
        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(juergen.login))
            .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(juergen.login))
            .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }

    @Test
    fun `List users`() {
        val juergen = User(login = "springjuergen", firstname = "Juergen", lastname =  "Hoeller")
        val smaldini = User(login = "smaldini", firstname = "Stéphane", lastname =  "Maldini")

        every { userRepository.findAll() } returns listOf(juergen, smaldini)
        mockMvc.perform(get("/api/user/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].login").value(juergen.login))
            .andExpect(jsonPath("\$.[1].login").value(smaldini.login))
    }
}