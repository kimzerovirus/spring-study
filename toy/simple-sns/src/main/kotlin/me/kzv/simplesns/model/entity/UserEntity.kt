package me.kzv.simplesns.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.kzv.simplesns.model.enum.UserRole
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Table(name = "\"user\"")
@SQLDelete(sql = "UPDATED \"user\" SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
@Entity
class UserEntity(
    val userName: String,

    val password: String,

    val deletedAt: LocalDateTime? = null
): BaseEntity() {

    var role: UserRole = UserRole.USER

    companion object{
        fun of(userName: String, password: String, passwordEncoder: PasswordEncoder) = UserEntity(userName = userName, password = passwordEncoder.encode(password))
    }
}