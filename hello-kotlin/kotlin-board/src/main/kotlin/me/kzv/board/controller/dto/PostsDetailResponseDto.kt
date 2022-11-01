package me.kzv.board.controller.dto

import me.kzv.board.entity.enums.LikeType

data class PostsDetailResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val writer: String,
    val like_cnt: Long,
    val dislike_cnt: Long,
    val i_liked: LikeType?,
    val comments: MutableList<PostsDetailCommentsResponseDto>
)

data class PostsDetailCommentsResponseDto(
        val writer: String,
        val content: String
)