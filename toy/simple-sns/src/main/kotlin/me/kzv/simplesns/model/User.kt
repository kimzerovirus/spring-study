package me.kzv.simplesns.model

import me.kzv.simplesns.model.entity.UserEntity
import me.kzv.simplesns.model.enum.UserRole
import java.time.LocalDateTime

class User(
    val id: Long?,
    val userName: String,
    val password: String,
    val userRole: UserRole,
    val registerAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val deletedAt: LocalDateTime?,
){
    companion object {
        fun fromEntity(entity: UserEntity) = User(
            id = entity.id,
            userName = entity.userName,
            password = entity.password,
            userRole = entity.role,
            registerAt = entity.registerAt,
            updatedAt = entity.updatedAt,
            deletedAt = entity.deletedAt,
        )
    }
}
