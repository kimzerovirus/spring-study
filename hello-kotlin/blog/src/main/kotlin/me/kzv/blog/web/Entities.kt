package me.kzv.blog.web

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Article(
    @Id @GeneratedValue var id: Long? = null,
    var title: String,
    var headline: String,
    var content: String,

    @ManyToOne var author: User,

    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
)

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue var id: Long? = null,
    var login: String,
    var firstname: String,
    var lastname: String,
    var description: String? = null,
)

/**
 * Notice that we are using here our String.toSlug() extension to provide a default argument to the slug parameter of Article constructor.
 * Optional parameters with default values are defined at the last position in order to make it possible to omit them when using positional arguments (Kotlin also supports named arguments).
 * Notice that in Kotlin it is not unusual to group concise class declarations in the same file.
 *
 * Here we don’t use data classes with val properties because JPA is not designed to work with immutable classes or the methods generated automatically by data classes.
 * If you are using other Spring Data flavor, most of them are designed to support such constructs so you should use classes like data class User(val login: String, …)
 * when using Spring Data MongoDB, Spring Data JDBC, etc.
 */