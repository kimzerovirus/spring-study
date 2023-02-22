package me.kzv.simplesns.service

import me.kzv.simplesns.exception.ErrorCode
import me.kzv.simplesns.exception.SimpleSnsException
import me.kzv.simplesns.model.User
import me.kzv.simplesns.model.entity.UserEntity
import me.kzv.simplesns.repository.UserEntityRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userEntityRepository: UserEntityRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun join(userName: String, password: String): User {
        userEntityRepository.findByUserName(userName)?.let { throw SimpleSnsException(ErrorCode.DUPLICATED_USER_NAME) }

        // 회원가입 user 등록
        val userEntity = userEntityRepository.save(UserEntity.of(userName = userName, password = password, passwordEncoder = passwordEncoder))

        return User.fromEntity(userEntity)
    }

    @Transactional
    fun login(userName: String, password: String): String {
        // 회원가입 여부 체크
        val userEntity = userEntityRepository.findByUserName(userName) ?: throw SimpleSnsException()

        // 비밀번호 체크
        if (userEntity.password != password) throw SimpleSnsException()

        // 토큰 생성

        return "";
    }

}
