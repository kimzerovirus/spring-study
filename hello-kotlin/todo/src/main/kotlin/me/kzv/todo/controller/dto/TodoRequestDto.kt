package me.kzv.todo.controller.dto

data class TodoRequestDto (
    val title: String,
    val description: String,
    val done: Boolean = false,
)