package me.kzv.todo.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "todos")
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,
    var title: String,
    var description: String,
    var done: Boolean,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
){
    fun update(title: String, description: String, done: Boolean) {
        this.title = title
        this.description = description
        this.done = done
        this.updatedAt = LocalDateTime.now()
    }
}