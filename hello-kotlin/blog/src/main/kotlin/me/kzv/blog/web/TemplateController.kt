package me.kzv.blog.web

import me.kzv.blog.config.BlogProperties
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
    private val repository: ArticleRepository,
    private val properties: BlogProperties
) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog" // JAVA : model.addAttribute("title", "Blog")
        model["banner"] = properties.banner
        model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    /**
     * Notice that we are using here a Kotlin extension that allows to add Kotlin functions or operators to existing Spring types.
     * Here we import the org.springframework.ui.set extension function in order to be able to write model["title"] = "Blog" instead of model.addAttribute("title", "Blog").
     * The Spring Framework KDoc API lists all the Kotlin extensions provided to enrich the Java API.
     */


    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = repository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

        model["title"] = article.title
        model["article"] = article

        return "article"
    }


    fun Article.render() = RenderedArticle(
        slug,
        title,
        headline,
        content,
        author,
        addedAt.format()
    )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String
    )

}
