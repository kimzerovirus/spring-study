package me.kzv.issue.domain

import me.kzv.issue.domain.enums.IssuePriority
import me.kzv.issue.domain.enums.IssueStatus
import me.kzv.issue.domain.enums.IssueType
import javax.persistence.*

@Entity
@Table
class Issue(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    val id: Long? = null,

    @Column
    var userId: Long,

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,

    @OneToMany(fetch = FetchType.EAGER)
    val comments : MutableList<Comment> = mutableListOf(),

    ) : BaseEntity()