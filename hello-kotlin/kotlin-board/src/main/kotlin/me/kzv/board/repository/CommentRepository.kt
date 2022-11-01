package me.kzv.board.repository

import me.kzv.board.entity.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByPostId(postId: Long): List<Comment>
    fun findAllByPostId(postId: Long, pageable: Pageable): List<Comment>

}