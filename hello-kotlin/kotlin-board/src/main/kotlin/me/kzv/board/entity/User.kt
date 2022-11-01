package me.kzv.board.entity

import javax.persistence.*

@Entity
class User private constructor(id: Long?, userId: String, password: String, name: String, age: Int) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        private set

    var userId: String = userId
        private set

    var password: String = password
        private set

    var name: String = name
        private set

    var age: Int = age
        private set

    constructor(userId: String, password: String, name: String, age: Int) : this(null, userId, password, name, age)
}