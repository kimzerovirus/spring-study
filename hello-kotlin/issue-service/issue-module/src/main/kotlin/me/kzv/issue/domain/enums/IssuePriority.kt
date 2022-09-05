package me.kzv.issue.domain.enums

enum class IssuePriority {

    LOW, MEDIUM, HIGH;

    companion object {
        operator fun invoke(priority: String) = IssueType.valueOf(priority.uppercase())
    }
}
