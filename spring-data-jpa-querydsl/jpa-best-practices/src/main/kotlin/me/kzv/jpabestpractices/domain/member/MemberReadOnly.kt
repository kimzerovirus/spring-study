package me.kzv.jpabestpractices.domain.member

import jakarta.persistence.*
import me.kzv.jpabestpractices.domain.team.Team
import me.kzv.jpabestpractices.supports.AuditableEntity

/**
 * 읽기전용 엔티티
 *
 * 하이버네이트가 제공하는 읽기전용
 * https://www.baeldung.com/hibernate-immutable
 */
@Table(name = "member")
@Entity
class MemberReadOnly(
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    val team: Team,

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    val status: MemberStatus,
) : AuditableEntity()
