package me.kzv.study.account

import me.kzv.study.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(email: String): Boolean
}