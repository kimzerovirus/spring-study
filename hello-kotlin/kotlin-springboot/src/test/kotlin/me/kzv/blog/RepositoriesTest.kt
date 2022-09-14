package me.kzv.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository,
    val articleRepository: ArticleRepository,
) {
    @Test
    fun `When findByIdOrNull then return Article`() {
        val testUser = User(
            id = 1,
            login = "Juergen",
            firstname = "kim",
            lastname = "zerovirus",
            description = "test",
        )
        entityManager.persist(testUser)

        val article = Article(
            id = 1,
            title = "Dear Spring community ...",
            headline = "Lorem ipsum",
            content = "test content",
            author = testUser
        )
        entityManager.persist(article)
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val testUser = User(
            id = 1,
            login = "Juergen",
            firstname = "kim",
            lastname = "zerovirus",
            description = "test",
        )
        entityManager.persist(testUser)
        entityManager.flush()

        val user = userRepository.findByLogin(testUser.login)
        assertThat(user).isEqualTo(testUser)
    }
}