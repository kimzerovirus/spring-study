package me.kzv.board.controller.dto

data class PostsResponseDto(
        val id: Long,
        val title: String,
        val content: String,
        val writer: String,
        val like_cnt: Long,
        val dislike_cnt: Long
)