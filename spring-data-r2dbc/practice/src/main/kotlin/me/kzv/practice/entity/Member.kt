package me.kzv.practice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("MEMBER")
class Member (
    @Id
    val id: Long,
    var name: String,
    var teamId: Long,
)