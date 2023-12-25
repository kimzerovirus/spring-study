package me.kzv.study.account

import me.kzv.study.domain.Account
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class AccountController (
    private val signUpFormValidator: SignUpFormValidator,
    private val accountService: AccountService,
){

    @InitBinder("signUpForm")
    fun initBinder(webDataBinder: WebDataBinder) {
        webDataBinder.addValidators(signUpFormValidator)
    }

    @GetMapping("/sign-up")
    fun signUpForm(model: Model): String {
        model.addAttribute("signupForm", SignupForm("","","")) // 객체와 attributeName 이 같으면 생략 가능
        return "account/sign-up"
    }

    @PostMapping("/sign-up")
    fun signUpSubmit(@Valid signUpForm: SignupForm, errors: Errors): String{
        if (errors.hasErrors()) {
            return "account/sign-up"
        }

        accountService.processNewAccount(signUpForm)

        return "redirect:/"
    }

}