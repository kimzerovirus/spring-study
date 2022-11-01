package me.kzv.board.repository

import me.kzv.board.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserIdAndPassword(userId: String, password: String): Optional<User>
}