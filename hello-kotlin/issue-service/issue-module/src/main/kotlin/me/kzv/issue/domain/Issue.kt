package me.kzv.issue.domain

import me.kzv.issue.domain.enums.IssuePriority
import me.kzv.issue.domain.enums.IssueType
import javax.persistence.*

@Entity
@Table
class Issue (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    ) : BaseEntity()