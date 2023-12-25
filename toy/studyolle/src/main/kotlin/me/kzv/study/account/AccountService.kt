package me.kzv.study.account

import me.kzv.study.domain.Account
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val javaMailSender: JavaMailSender,
) {

    fun processNewAccount(signUpForm: SignupForm) {
        val newAccount = saveNewAccount(signUpForm)
        newAccount.generateEmailCheckToken()
        sendSignUpConfirmEmail(newAccount)
    }

    private fun saveNewAccount(signUpForm: SignupForm): Account {
        val account = Account(
            email = signUpForm.email,
            nickname = signUpForm.nickname,
            password = signUpForm.password,
        )

        return accountRepository.save(account)
    }

    private fun sendSignUpConfirmEmail(newAccount: Account) {
        val mailMessage = SimpleMailMessage()
        mailMessage.setSubject("스터디올래, 회원 가입 인증") // 제목
        mailMessage.setText("/check-email-token?token=${newAccount.emailCheckToken}&email=${newAccount.email}")// 본문

        javaMailSender.send(mailMessage)
    }
}