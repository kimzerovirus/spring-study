package me.kzv.issue.service

import me.kzv.issue.config.AuthUser
import me.kzv.issue.domain.Comment
import me.kzv.issue.domain.CommentRepository
import me.kzv.issue.domain.Issue
import me.kzv.issue.domain.IssueRepository
import me.kzv.issue.exception.NotFoundException
import me.kzv.issue.web.dto.CommentRequest
import me.kzv.issue.web.dto.CommentResponse
import me.kzv.issue.web.dto.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest) : CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUserId(id, userId)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        commentRepository.findByIdAndUserId(id, userId)?.let {
            comment -> issue.comments.remove(comment)
        }
    }
}