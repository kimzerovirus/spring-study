package me.kzv.board.service

import me.kzv.board.entity.Comment
import me.kzv.board.repository.CommentRepository
import me.kzv.board.controller.dto.CommentWriteRequestDto
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CommentService(val commentRepository: CommentRepository) {

    fun writeComment(dto: CommentWriteRequestDto): Long {
        val comment = Comment(content = dto.content, writer = dto.writer, userId = dto.userId, postId = dto.postId)
        return commentRepository.save(comment).id!!
    }

    fun deleteComment(commentId: Long) {
        val comment = commentRepository.findById(commentId)
                .orElseThrow { RuntimeException() }
        commentRepository.delete(comment)
    }
}