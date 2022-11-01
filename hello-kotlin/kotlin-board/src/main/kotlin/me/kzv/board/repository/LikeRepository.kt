package me.kzv.board.repository

import me.kzv.board.entity.Like
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LikeRepository : JpaRepository<Like, Long> {
    fun findByUserIdAndPostId(userId: Long, postId: Long): Optional<Like>
    fun findAllByPostId(postId: Long): List<Like>
}