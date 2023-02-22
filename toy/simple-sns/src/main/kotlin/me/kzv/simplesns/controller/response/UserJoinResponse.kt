package me.kzv.simplesns.controller.response

import me.kzv.simplesns.model.User
import me.kzv.simplesns.model.enum.UserRole

class UserJoinResponse(
    val userName: String,
    val id: Long?,
    val role: UserRole,
) {
    companion object {
        fun fromUser(user: User) = UserJoinResponse(
            userName = user.userName,
            id = user.id,
            role = user.userRole,
        )
    }
}