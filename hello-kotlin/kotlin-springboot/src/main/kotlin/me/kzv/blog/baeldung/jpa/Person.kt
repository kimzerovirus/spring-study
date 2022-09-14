package me.kzv.blog.baeldung.jpa

import javax.persistence.*

@Entity
class Person (
    @Column(nullable = false)
    val name: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
)