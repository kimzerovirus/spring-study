package me.kzv.simplesns.service

import me.kzv.simplesns.exception.SimpleSnsException
import me.kzv.simplesns.fixture.UserEntityFixture
import me.kzv.simplesns.model.entity.UserEntity
import me.kzv.simplesns.repository.UserEntityRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
class UserServiceTest {
    @Autowired lateinit var userService: UserService
    @Autowired lateinit var userEntityRepository: UserEntityRepository
    @Autowired lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `회원가입이 정상적으로 동작하는 경우`() {
        //given
        val userName = "userName"
        val password = "password"

        //when
        Mockito.`when`(userEntityRepository.findByUserName(userName)).thenReturn(null) // Optional.empty() -> null
        Mockito.`when`(passwordEncoder.encode(password)).thenReturn("encrypt_password")
        Mockito.`when`(userEntityRepository.save(any())).thenReturn(mock(UserEntity::class.java))

        //then
        Assertions.assertDoesNotThrow { userService.join(userName = userName, password = password) }
    }

    @Test
    fun `회원가입시 userName으로 회원 가입한 유저가 이미 있는 경우`() {
        //given
        val userName = "userName"
        val password = "password"

        //when
        Mockito.`when`(userEntityRepository.findByUserName(userName)).thenReturn(mock(UserEntity::class.java))
        Mockito.`when`(passwordEncoder.encode(password)).thenReturn("encrypt_password")
        Mockito.`when`(userEntityRepository.save(any())).thenReturn(mock(UserEntity::class.java))

        //then
        Assertions.assertThrows(SimpleSnsException::class.java) { userService.join(userName = userName, password = password) }
    }

    @Test
    fun `로그인이 정상적으로 동작하는 경우`() {
        //given
        val userName = "userName"
        val password = "password"
        val fixture = UserEntityFixture.get(userName, password)

        //when
        Mockito.`when`(userEntityRepository.findByUserName(userName)).thenReturn(fixture)

        //then
        Assertions.assertDoesNotThrow { userService.login(userName = userName, password = password) }
    }

    @Test
    fun `로그인시 userName으로 회원가입한 유저가 없는 경우`() {
        //given
        val userName = "userName"
        val password = "password"

        //when
        Mockito.`when`(userEntityRepository.findByUserName(userName)).thenReturn(null)

        //then
        Assertions.assertThrows(SimpleSnsException::class.java) { userService.login(userName = userName, password = password) }
    }

    @Test
    fun `로그인시 패스워드가 틀린 경우`() {
        //given
        val userName = "userName"
        val password = "password"
        val wrongPassword = "wrongPassword"
        val fixture = UserEntityFixture.get(userName, password)

        //when
        Mockito.`when`(userEntityRepository.findByUserName(userName)).thenReturn(fixture)

        //then
        Assertions.assertThrows(SimpleSnsException::class.java) { userService.login(userName = userName, password = wrongPassword) }
    }
}