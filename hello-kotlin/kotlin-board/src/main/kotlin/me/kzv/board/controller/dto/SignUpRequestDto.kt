package me.kzv.board.controller.dto

data class SignUpRequestDto(
        val userId: String,
        val password: String,
        val name: String,
        val age: Int
)