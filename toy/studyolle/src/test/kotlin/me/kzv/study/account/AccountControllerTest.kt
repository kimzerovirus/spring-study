package me.kzv.study.account

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest{
    @Autowired lateinit var mockMvc: MockMvc
    @Autowired lateinit var accountRepository: AccountRepository
    @MockBean lateinit var javaMailSender: JavaMailSender

    @Test
    fun `회원 가입 화면 보이는지 테스트`() {
        //given

        //when

        //then
        mockMvc.perform(get("/sign-up"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(view().name("account/sign-up"))
            .andExpect(model().attributeExists("signupForm"))
    }

    @Test
    fun `회원 가입 처리 - 입력값 오류`() {
        //given

        //when

        //then
        mockMvc.perform(
            post("/sign-up")
                .param("nickname", "yul")
                .param("email", "email@email.com")
                .param("password", "12345")
                .with(csrf())
        )
//            .andExpect(status().isOk)
            .andExpect(status().is3xxRedirection)
            .andExpect(view().name("account/sign-up"))

        assertTrue(accountRepository.existsByEmail("email@email.com"))
        then(javaMailSender).should().send(any(SimpleMailMessage::class.java))
        // javaMailSender 는 smtp 서버와 연결만 해주면 되므로 인터페이스만 관리하면 된다 - 실제로 이메일을 보내는 어플리케이션은 gmail 과 같은 smtp 서버
    }

}