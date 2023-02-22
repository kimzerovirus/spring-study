package me.kzv.simplesns.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.kzv.simplesns.controller.request.UserJoinRequest
import me.kzv.simplesns.controller.request.UserLoginRequest
import me.kzv.simplesns.exception.SimpleSnsException
import me.kzv.simplesns.model.User
import me.kzv.simplesns.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.lang.RuntimeException

// kotlin mockito
// https://www.baeldung.com/kotlin/mockito

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var objectMapper: ObjectMapper
    @Autowired lateinit var userService: UserService

    @Test
    fun `회원가입 성공`() {
        //given
        val userName = "userName"
        val password = "password"
        val userRequest = UserJoinRequest(userName = userName, password = password)

        //when
        Mockito.`when`(userService.join(userName = userName, password = password)).thenReturn(mock(User::class.java))

        //then
        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userRequest))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
    }

    @Test
    fun `회원가입 실패 - 이미 회원가입된 유저가 있는 경우`() {
        //given
        val userName = "userName"
        val password = "password"
        val userRequest = UserJoinRequest(userName, password)

        //when
        Mockito.`when`(userService.join(userName = userName, password = password)).thenThrow(SimpleSnsException())

        //then
        mockMvc.perform(post("/api/v1/users/join")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(userRequest))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isConflict)
    }

    @Test
    fun `로그인 성공`() {
        //given
        val userName = "userName"
        val password = "password"
        val userRequest = UserLoginRequest(userName, password)

        //when
        Mockito.`when`(userService.login(userName = userName, password = password)).thenReturn("test_token")

        //then
        mockMvc.perform(post("/api/v1/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(userRequest))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
    }

    @Test
    fun `로그인 실패 - 로그인시 회원가입이 안된 userName을 입력한 경우`() {
        //given
        val userName = "userName"
        val password = "password"
        val userRequest = UserLoginRequest(userName = userName, password = password)

        //when
        Mockito.`when`(userService.login(userName = userName, password = password)).thenThrow(SimpleSnsException())

        //then
        mockMvc.perform(post("/api/v1/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(userRequest))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isNotFound)
    }

    @Test
    fun `로그인 실패 - 로그인시 잘못된 password를 입력한 경우`() {
        //given
        val userName = "userName"
        val password = "password"
        val userRequest = UserLoginRequest(userName = userName, password = password)

        //when
        Mockito.`when`(userService.login(userName = userName, password = password)).thenThrow(SimpleSnsException())

        //then
        mockMvc.perform(post("/api/v1/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(userRequest))
        ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isUnauthorized)
    }
}