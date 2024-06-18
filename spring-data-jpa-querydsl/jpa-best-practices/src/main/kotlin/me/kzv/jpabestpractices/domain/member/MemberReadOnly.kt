package me.kzv.jpabestpractices.domain.member

import jakarta.persistence.*
import me.kzv.jpabestpractices.domain.team.Team
import me.kzv.jpabestpractices.supports.AuditableEntity
import org.hibernate.annotations.Immutable

/**
 * 읽기전용 엔티티 만들기
 *
 * 하이버네이트가 제공하는 읽기전용
 * https://www.baeldung.com/hibernate-immutable
 */
@Entity
@Immutable
@Table(name = "tbl_member")
class MemberReadOnly(
    @ManyToOne(fetch = FetchType.LAZY) // 연관관계는 insertable, updatable 걸면 안되는듯
    val team: Team,

    @Column(insertable = false, updatable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    val status: MemberStatus = MemberStatus.ACTIVE,
) : AuditableEntity()
