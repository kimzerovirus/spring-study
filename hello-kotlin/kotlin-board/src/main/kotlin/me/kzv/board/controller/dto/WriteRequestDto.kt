package me.kzv.board.controller.dto

data class CommentWriteRequestDto(
        val userId: Long,
        val postId: Long,
        val writer: String,
        val content: String
)

data class PostWriteRequestDto(
        val userId: Long,
        val title: String,
        val content: String
)