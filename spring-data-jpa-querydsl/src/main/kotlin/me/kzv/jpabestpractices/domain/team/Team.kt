package me.kzv.jpabestpractices.domain.team

import jakarta.persistence.*
import me.kzv.jpabestpractices.supports.AuditableEntity

@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_team", columnNames = ["team_name"])]
)
@Entity
class Team (
    @Column(name = "team_name", nullable = false, length = 50)
    var teamName: String,
):AuditableEntity()