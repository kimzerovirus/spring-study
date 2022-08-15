package me.kzv.todo.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import me.kzv.todo.domain.Todo

data class TodoListResponseDto(
    val items: List<TodoResponseDto>
) {
    val size: Int
        @JsonIgnore
        get() = items.size

    fun get(index: Int) = items[index]

    companion object {
        fun of(todoList: List<Todo>) = TodoListResponseDto(todoList.map(TodoResponseDto::of))
        // fun of(todoList: List<Todo>) = TodoListResponseDto(todoList.map { todo -> TodoResponseDto.of(todo) })
    }
}