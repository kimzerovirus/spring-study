package me.kzv.todo.controller.dto

import me.kzv.todo.domain.Todo
import java.time.LocalDateTime

// dto 는 Immutable
data class TodoResponseDto(
    val id: Long,
    val title: String,
    val description: String,
    val done: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
) {
    // 코틀린은 static 이 존재하지 않고 이렇게 companion object 를 사용한다.
    // companion object : 클래스와 동행하는 유일한 오브젝트
    companion object {
        fun of(todo: Todo): TodoResponseDto{
            checkNotNull(todo.id)

            return TodoResponseDto(
                id = todo.id,
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt,
            )
        }
    }
}