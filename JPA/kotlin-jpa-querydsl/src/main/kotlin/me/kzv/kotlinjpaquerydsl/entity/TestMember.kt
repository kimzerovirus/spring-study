package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TestMember() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var username: String = ""

    constructor(username: String) : this() {
        this.username = username
    }
}
