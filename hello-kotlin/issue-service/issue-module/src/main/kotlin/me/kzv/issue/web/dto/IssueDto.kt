package me.kzv.issue.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import me.kzv.issue.domain.Comment
import me.kzv.issue.domain.Issue
import me.kzv.issue.domain.enums.IssuePriority
import me.kzv.issue.domain.enums.IssueStatus
import me.kzv.issue.domain.enums.IssueType
import java.time.LocalDateTime

data class IssueRequest(
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
)

data class IssueResponse(
    val id: Long,
    val summary: String,
    val description: String,
    val userId: Long,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,

    val comments: List<CommentResponse> = emptyList(),

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
) {
    companion object {
        // invoke 함수는 따로 메서드 선언 없이 생성자 처럼 바로 사용 가능해서 좋음
        operator fun invoke(issue: Issue) = with(issue) {
            IssueResponse(
                id = id!!,
                comments = comments.sortedByDescending(Comment::id).map(Comment::toResponse),
                summary = summary,
                description = description,
                userId = userId,
                type = type,
                priority = priority,
                status = status,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}