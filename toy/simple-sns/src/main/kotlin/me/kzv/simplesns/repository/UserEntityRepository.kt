package me.kzv.simplesns.repository

import me.kzv.simplesns.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository: JpaRepository<UserEntity, Long> {
    fun findByUserName(userName: String): UserEntity?
}