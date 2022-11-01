package me.kzv.board.repository

import me.kzv.board.entity.Post
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByUserId(userId: Long, pageable: Pageable): List<Post>
}