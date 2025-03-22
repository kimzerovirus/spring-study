package me.kzv.practice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("TEAM")
class Team (
    @Id
    val id: Long,
    var name: String,
)