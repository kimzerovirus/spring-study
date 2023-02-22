package me.kzv.simplesns.fixture

import me.kzv.simplesns.model.entity.UserEntity

class UserEntityFixture {
    companion object {
        fun get(userName: String, password: String): UserEntity {
            return UserEntity(userName = userName, password = password).apply { this.id = 1 }
        }
    }
}