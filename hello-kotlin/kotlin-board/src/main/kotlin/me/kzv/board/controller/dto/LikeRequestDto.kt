package me.kzv.board.controller.dto

import me.kzv.board.entity.enums.LikeType

data class LikeRequestDto(
        val userId: Long,
        val postId: Long,
        val likeType: LikeType
)