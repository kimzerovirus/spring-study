package me.kzv.jpabestpractices.domain.member

import jakarta.persistence.*
import me.kzv.jpabestpractices.domain.team.Team
import me.kzv.jpabestpractices.supports.AuditableEntity

@Entity
class Member(
    @JoinColumn(
        name = "team_id",
        foreignKey = ForeignKey(name = "fk_team_id"),
        nullable = false,
    )
    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team,

    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.ACTIVE
) : AuditableEntity() {

}