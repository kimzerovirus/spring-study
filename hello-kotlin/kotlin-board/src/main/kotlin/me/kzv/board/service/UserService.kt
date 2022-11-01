package me.kzv.board.service

import me.kzv.board.entity.User
import me.kzv.board.repository.UserRepository
import me.kzv.board.controller.dto.SignInRequestDto
import me.kzv.board.controller.dto.SignUpRequestDto
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService(val userRepository: UserRepository) {

    fun signUp(dto: SignUpRequestDto): Long {
        val user = User(userId = dto.userId, password = dto.password, name = dto.name, age = dto.age)
        return userRepository.save(user).id!!
    }

    fun signIn(dto: SignInRequestDto): Long {
        return userRepository.findByUserIdAndPassword(dto.userId, dto.password)
                .orElseThrow { RuntimeException() }.id!!
    }
}
