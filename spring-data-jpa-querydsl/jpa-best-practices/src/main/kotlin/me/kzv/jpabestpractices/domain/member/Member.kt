package me.kzv.jpabestpractices.domain.member

import jakarta.persistence.*
import me.kzv.jpabestpractices.domain.team.Team
import me.kzv.jpabestpractices.supports.AuditableEntity
import org.hibernate.annotations.Where

@Entity
@Table(name = "tbl_member")
@Where(clause = "status = 'ACTIVE'")
class Member(
    @JoinColumn(
        name = "team_id",
        foreignKey = ForeignKey(name = "fk_team_id"),
        nullable = false,
    )
    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team,

    val name: String,

    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.ACTIVE
) : AuditableEntity() {

}